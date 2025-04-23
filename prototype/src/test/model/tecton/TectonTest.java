package test.model.tecton;

import controller.Game;
import model.Map;
import model.insect.Insect;
import model.insect.Insecter;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.mushroom.spore.ClawParalyzingSpore;
import model.tecton.Tecton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TectonTest {
    private Tecton tecton, neighbour1, neighbour2, newTecton;
    private Mushroomer mushroomer;
    private Insecter insecter;

    @BeforeEach
    public void setUp() {
        Game.random.setSeed(1);
        mushroomer = new Mushroomer();
        insecter = new Insecter();

        tecton = new Tecton();

        neighbour1 = new Tecton();
        Map.connect(tecton, neighbour1);

        neighbour2 = new Tecton();
        Map.connect(tecton, neighbour2);

        newTecton = new Tecton();
        Map.connect(neighbour1, newTecton);
    }

    @Test
    public void testGetNeighbours() {
        assert (tecton.getNeighbours().contains(neighbour1));
        assert (tecton.getNeighbours().contains(neighbour2));
    }

    @Test
    public void testAddNeighbour() {
        Tecton newNeighbour = new Tecton();
        tecton.addNeighbour(newNeighbour);
        newNeighbour.addNeighbour(tecton);

        assert (tecton.getNeighbours().contains(newNeighbour));
    }

    @Test
    public void testDistanceTo() {
        assertEquals(0, tecton.distanceTo(tecton));
        assertEquals(1, tecton.distanceTo(neighbour1));
        assertEquals(2, tecton.distanceTo(newTecton));
    }

    @Test
    public void testSplit() {
        mushroomer = new Mushroomer();
        tecton.add(new MushroomStem(mushroomer, tecton));
        tecton.add(new MushroomThread(mushroomer, tecton));
        tecton.add(new ClawParalyzingSpore(mushroomer, tecton));

        newTecton = tecton.split();

        assertNotNull(newTecton);
        assertNotSame(tecton, newTecton);
        assertEquals(0, tecton.getThreads().size());
    }

    @Test
    public void testIStem() {
        MushroomStem stem = new MushroomStem(mushroomer, tecton);

        assertTrue(tecton.add(stem));
        assertTrue(tecton.getStems().contains(stem));
        assertEquals(stem, tecton.getStem(stem.getId()));
        assertFalse(tecton.add(stem));

        assertTrue(tecton.remove(stem));
        assertFalse(tecton.hasStem());
    }

    @Test
    public void testISpore() {
        ClawParalyzingSpore spore = new ClawParalyzingSpore(mushroomer, tecton);

        assertTrue(tecton.add(spore));
        assertTrue(tecton.getSpores().contains(spore));
        assertEquals(spore, tecton.getSpore(spore.getId()));

        assertTrue(tecton.remove(spore));
        assertFalse(tecton.getSpores().contains(spore));
    }

    @Test
    public void testIThread() {
        MushroomThread thread = new MushroomThread(mushroomer, tecton);

        assertTrue(tecton.add(thread));
        assertTrue(tecton.getThreads().contains(thread));
        assertEquals(thread, tecton.getThread(thread.getId()));

        assertTrue(tecton.remove(thread));
        assertFalse(tecton.getThreads().contains(thread));
    }

    @Test
    public void testIInsect() {
        Insect insect = new Insect(insecter, tecton);

        assertTrue(tecton.add(insect));
        assertTrue(tecton.getInsects().contains(insect));
        assertEquals(insect, tecton.getInsect(insect.getId()));

        assertTrue(tecton.remove(insect));
        assertFalse(tecton.getInsects().contains(insect));
    }

    @Test
    public void testGetConnectedNeighbours() {
        tecton.add(new MushroomThread(mushroomer, tecton));
        neighbour1.add(new MushroomThread(mushroomer, neighbour1));
        neighbour2.add(new MushroomThread(mushroomer, neighbour2));
        List<Tecton> connectedNeighbours = tecton.getConnectedNeighbours(mushroomer);

        assertEquals(2, connectedNeighbours.size());
        assertTrue(connectedNeighbours.contains(neighbour1));
        assertTrue(connectedNeighbours.contains(neighbour2));
    }

    @Test
    public void testRemoveUnconnectedThreads() {
        tecton.add(new MushroomThread(mushroomer, tecton));
        tecton.add(new MushroomThread(mushroomer, tecton));
        tecton.add(new MushroomStem(mushroomer, tecton));
        newTecton.add(new MushroomThread(mushroomer, tecton));

        tecton.removeUnconnectedThreads();
        assertFalse(tecton.getThreads().isEmpty());

        newTecton.removeUnconnectedThreads();
        assertTrue(newTecton.getThreads().isEmpty());
    }

    @Test
    public void testEquals() {
        assertEquals(tecton, tecton);

        assertNotEquals(tecton, newTecton);

        assertNotEquals(null, tecton);

        assertNotEquals(new Object(), tecton);

        assertEquals(tecton, new Tecton(tecton));
    }
}
