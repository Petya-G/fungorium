package test.model.mushroom;

import model.insect.Insect;
import model.insect.Insecter;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.tecton.Tecton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MushroomThreadTest {

    private Mushroomer mushroomer;
    private Tecton tecton;
    private MushroomThread thread;

    @BeforeEach
    void setup() {
        mushroomer = new Mushroomer();
        tecton = new Tecton();
        thread = new MushroomThread(mushroomer, tecton);
    }

    @Test
    void testHasEaten() {
        assertFalse(thread.hasEaten());
        thread.setEaten(true);
        assertTrue(thread.hasEaten());
    }

    @Test
    void testEat_ParalyzedInsect() {
        Insecter insecter = new Insecter();
        Insect insect = new Insect(insecter, tecton);
        insect.setParalyzed(true);
        assertTrue(thread.eat(insect));
        assertTrue(thread.hasEaten());
    }

    @Test
    void testEat_Cutoff() {
        thread.setCutOff(true);
        Insecter insecter = new Insecter();
        Insect insect = new Insect(insecter, tecton);
        insect.setParalyzed(true);
        assertFalse(thread.eat(insect));
        assertFalse(thread.hasEaten());
    }

    @Test
    void testEat_NotParalyzed() {
        Insecter insecter = new Insecter();
        Insect insect = new Insect(insecter, tecton);
        insect.setParalyzed(false);
        assertFalse(thread.eat(insect));
        assertFalse(thread.hasEaten());
    }

    @Test
    void testIsConnected_Failure() {
        assertFalse(thread.isConnected());
    }

    @Test
    void testIsConnected_Failure2() {
        Tecton tecton2=new Tecton();
        Tecton tecton3=new Tecton();
        tecton2.addNeighbour(tecton);
        tecton.addNeighbour(tecton2);

        tecton2.addNeighbour(tecton3);
        tecton3.addNeighbour(tecton2);

        MushroomStem stem = new MushroomStem(mushroomer, tecton3);
        tecton3.add(stem);
        mushroomer.add(stem);
        assertFalse(thread.isConnected());
    }

    @Test
    void testIsConnected_Success1() {
        MushroomStem stem = new MushroomStem(mushroomer, tecton);
        tecton.add(stem);
        mushroomer.add(stem);
        assertTrue(thread.isConnected());
    }

    @Test
    void testIsConnected_Success2() {
        Tecton tecton2=new Tecton();
        Tecton tecton3=new Tecton();
        tecton2.addNeighbour(tecton);
        tecton.addNeighbour(tecton2);

        tecton2.addNeighbour(tecton3);
        tecton3.addNeighbour(tecton2);

        MushroomStem stem = new MushroomStem(mushroomer, tecton3);
        tecton3.add(stem);
        mushroomer.add(stem);

        MushroomThread thread2 = new MushroomThread(mushroomer, tecton2);
        tecton2.add(thread2);
        mushroomer.add(thread2);
        MushroomThread thread3 = new MushroomThread(mushroomer, tecton3);
        tecton3.add(thread3);
        mushroomer.add(thread3);

        assertTrue(thread.isConnected());
    }

    @Test
    void testIsConnected_OtherStem() {
        Mushroomer mushroomer2 = new Mushroomer();
        MushroomStem stem = new MushroomStem(mushroomer2, tecton);
        tecton.add(stem);
        mushroomer2.add(stem);
        assertFalse(thread.isConnected());
    }

    @Test
    void testRemove(){

        thread.setCutOff(true);
        for(int i=0; i<thread.getMaxCutOffDuration(); i++){
            thread.endTurn();
        }

        assertFalse(mushroomer.getThreads().contains(thread));
        assertFalse(tecton.getThreads().contains(thread));
    }
}
