package test.model.InsectTest;
import model.mushroom.*;
import model.mushroom.spore.ParalyzingSpore;
import model.mushroom.spore.SpeedingSpore;
import model.mushroom.spore.SplitterSpore;
import model.mushroom.spore.Spore;
import model.tecton.Tecton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.Game;
import model.Map;
import model.insect.*;
import model.tecton.*;
import model.effect.*;
import static org.junit.jupiter.api.Assertions.*;
public class InsectTest {
    private Insecter insecter;
    private Tecton tecton;
    private Tecton neighbour1;
    private Tecton neighbour2;
    private Tecton newTecton;
    private Insect insect;
    private Spore spore;
    private Mushroomer mushroomer;
    private MushroomThread thread;
    private MushroomThread thread2;
    private Spore splitSpore;

    @BeforeEach
    public void setUpTectons() {
        Game.random.setSeed(1);
        insecter = new Insecter();

        tecton = new Tecton();

        neighbour1 = new Tecton();
        Map.connect(tecton, neighbour1);

        neighbour2 = new Tecton();
        Map.connect(tecton, neighbour2);

        newTecton = new Tecton();
        Map.connect(neighbour1, newTecton);

        insect = new Insect(insecter,tecton);
        mushroomer = new Mushroomer();
        
        
        

    }

    @Test
    public void moveTest(){
        thread = new MushroomThread(mushroomer,tecton);
        thread2 = new MushroomThread(mushroomer,neighbour1);
        insect.move(neighbour1);
        assertTrue(neighbour1.getInsects().contains(insect));
    }
    @Test
    public void moveFailTest(){
        thread = new MushroomThread(mushroomer,tecton);
        thread2 = new MushroomThread(mushroomer,neighbour1);
        insect.move(neighbour2);
        assertFalse(neighbour2.getInsects().contains(insect));
    }

    @Test
    public void eatTest(){
        spore = new SpeedingSpore(mushroomer, tecton);
        insect.eat(spore);
        assertEquals(insect.getSpeedModifier(),2 );
    }

    @Test
    public void splitSporeTest(){
        splitSpore = new SplitterSpore(mushroomer, tecton);
        insect.eat(splitSpore);
        int insectCount = insecter.getInsects().size();
        assertEquals(insectCount, 2);
        
    }





}
