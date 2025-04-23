package test.model.mushroom;

import model.core.Player;
import model.insect.Insect;
import model.insect.Insecter;
import model.mushroom.*;
import model.mushroom.spore.ParalyzingSpore;
import model.mushroom.spore.Spore;
import model.tecton.Tecton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MushroomerTest {

    private Mushroomer mushroomer;
    private Tecton tecton;


    @BeforeEach
    void setup() {
        mushroomer = new Mushroomer();
        tecton = new Tecton();
    }

    @Test
    void testConstructor_Tecton() {
        Tecton tecton2=new Tecton();
        Mushroomer mushroomer2 = new Mushroomer(tecton2);

        assertTrue(mushroomer2.hasThread(tecton2));
        assertTrue(mushroomer2.hasStem(tecton2));
    }

    @Test
    void testGetStems() {
        MushroomStem ms=new MushroomStem(mushroomer, tecton);
        mushroomer.add(ms);
        tecton.add(ms);
        assertTrue(mushroomer.hasStem(tecton));

        assertEquals(mushroomer.getStems(), Arrays.asList(ms));
    }

    @Test
    void testGetStems_NoStems() {
        assertFalse(mushroomer.hasStem(tecton));
        assertEquals(mushroomer.getStems(), Arrays.asList());
    }

    @Test
    void testHasStems() {
        MushroomStem ms=new MushroomStem(mushroomer, tecton);
        mushroomer.add(ms);
        tecton.add(ms);
        assertTrue(mushroomer.hasStem(tecton));
    }

    @Test
    void testHasStems_NoStems() {
        assertFalse(mushroomer.hasStem(tecton));
    }

    @Test
    void testGetThreads() {
        MushroomThread mt=new MushroomThread(mushroomer, tecton);
        mushroomer.add(mt);
        tecton.add(mt);
        assertTrue(mushroomer.hasThread(tecton));

        assertEquals(mushroomer.getThreads(), Arrays.asList(mt));
    }

    @Test
    void testGetThreads_NoThreads() {
        assertFalse(mushroomer.hasThread(tecton));
        assertEquals(mushroomer.getThreads(), Arrays.asList());
    }

    @Test
    void testHasThreads() {
        MushroomThread mt=new MushroomThread(mushroomer, tecton);
        mushroomer.add(mt);
        tecton.add(mt);
        assertTrue(mushroomer.hasThread(tecton));
    }

    @Test
    void testHasThreads_NoThreads() {
        assertFalse(mushroomer.hasThread(tecton));
    }

    @Test
    void testGetSpores() {
        Spore s1=new ParalyzingSpore(mushroomer, tecton);
        Spore s2=new ParalyzingSpore(mushroomer, tecton);
        Spore s3=new ParalyzingSpore(mushroomer, tecton);
        mushroomer.add(s1);
        mushroomer.add(s2);
        mushroomer.add(s3);
        assertEquals(mushroomer.getSpores(), Arrays.asList(s1, s2, s3));
    }

    @Test
    void testGetSpores_Tecton() {
        Tecton tecton2=new Tecton();

        Spore s1=new ParalyzingSpore(mushroomer, tecton);
        Spore s2=new ParalyzingSpore(mushroomer, tecton);
        Spore s3=new ParalyzingSpore(mushroomer, tecton2);

        mushroomer.add(s1);
        mushroomer.add(s2);
        mushroomer.add(s3);
        assertEquals(mushroomer.getSpores(tecton), Arrays.asList(s1, s2));
    }

    @Test
    void testPlantMushroomStem_EnoughSpores() {
        MushroomThread thread = new MushroomThread(mushroomer, tecton);
        tecton.add(thread);
        mushroomer.add(thread);

        MushroomStem stem = new MushroomStem(mushroomer, tecton);

        for(int i=0; i<stem.getLevelUpCost(); i++){
            Spore s1=new ParalyzingSpore(mushroomer, tecton);
            tecton.add(s1);
            mushroomer.add(s1);
        }

        assertTrue(mushroomer.plantMushroomStem(tecton));
    }

    @Test
    void testPlantMushroomStem_NotEnoughSpores() {
        MushroomThread thread = new MushroomThread(mushroomer, tecton);
        tecton.add(thread);
        mushroomer.add(thread);

        MushroomStem stem = new MushroomStem(mushroomer, tecton);
        for(int i=0; i<stem.getLevelUpCost()-1; i++){
            Spore s1=new ParalyzingSpore(mushroomer, tecton);
            tecton.add(s1);
            mushroomer.add(s1);
        }

        assertFalse(mushroomer.plantMushroomStem(tecton));
    }


    @Test
    void testPlantMushroomStem_AlreadyHasStem() {
        MushroomThread thread = new MushroomThread(mushroomer, tecton);
        tecton.add(thread);
        mushroomer.add(thread);

        MushroomStem stem2= new MushroomStem(mushroomer, tecton);
        tecton.add(stem2);
        mushroomer.add(stem2);


        for(int i=0; i<stem2.getLevelUpCost(); i++){
            Spore s1=new ParalyzingSpore(mushroomer, tecton);
            tecton.add(s1);
            mushroomer.add(s1);
        }

        assertFalse(mushroomer.plantMushroomStem(tecton));
    }

    @Test
    void testPlantMushroomStem_NoThread() {
        MushroomStem stem = new MushroomStem(mushroomer, tecton);
        for(int i=0; i<stem.getLevelUpCost(); i++){
            Spore s1=new ParalyzingSpore(mushroomer, tecton);
            tecton.add(s1);
            mushroomer.add(s1);
        }

        assertFalse(mushroomer.plantMushroomStem(tecton));
    }

    @Test
    void testPlantMushroomStem_HasEaten() {
        MushroomThread thread = new MushroomThread(mushroomer, tecton);
        tecton.add(thread);
        mushroomer.add(thread);

        thread.setEaten(true);
        assertTrue(mushroomer.plantMushroomStem(tecton));
        assertFalse(thread.hasEaten());
    }

    @Test
    void testGrowMushroomThread() {
        MushroomStem stem = new MushroomStem(mushroomer, tecton);
        tecton.add(stem);
        mushroomer.add(stem);

        MushroomThread thread = new MushroomThread(mushroomer, tecton);
        tecton.add(thread);
        mushroomer.add(thread);

        for(int i=0; i<mushroomer.getMaxThreadsPerTurn(); i++){
            Tecton t1=new Tecton();
            tecton.addNeighbour(t1); // alapból map csinálná
            t1.addNeighbour(tecton); // alapból map csinálná
            assertTrue(mushroomer.growMushroomThread(t1));
        }
    }

    @Test
    void testGrowMushroomThread_AlreadyGrown() {
        MushroomStem stem = new MushroomStem(mushroomer, tecton);
        tecton.add(stem);
        mushroomer.add(stem);

        MushroomThread thread = new MushroomThread(mushroomer, tecton);
        tecton.add(thread);
        mushroomer.add(thread);

        for(int i=0; i<mushroomer.getMaxThreadsPerTurn(); i++){
            Tecton t1=new Tecton();
            tecton.addNeighbour(t1); // alapból map csinálná
            t1.addNeighbour(tecton); // alapból map csinálná
            assertTrue(mushroomer.growMushroomThread(t1));
        }


        Tecton t2=new Tecton();
        tecton.addNeighbour(t2);

        assertFalse(mushroomer.growMushroomThread(t2));
    }

    @Test
    void testGrowMushroomThread_AlreadyHasThread() {
        MushroomStem stem = new MushroomStem(mushroomer, tecton);
        tecton.add(stem);
        mushroomer.add(stem);

        MushroomThread thread = new MushroomThread(mushroomer, tecton);
        tecton.add(thread);
        mushroomer.add(thread);

        assertFalse(mushroomer.growMushroomThread(tecton));
    }

    @Test
    void testGrowMushroomThread_NeighbourNoThread() {
        MushroomStem stem = new MushroomStem(mushroomer, tecton);
        tecton.add(stem);
        mushroomer.add(stem);

        MushroomThread thread = new MushroomThread(mushroomer, tecton);
        tecton.add(thread);
        mushroomer.add(thread);

        Tecton tecton2=new Tecton();
        tecton2.addNeighbour(tecton);
        tecton.addNeighbour(tecton2);

        assertFalse(mushroomer.growMushroomThread(tecton));
    }

}