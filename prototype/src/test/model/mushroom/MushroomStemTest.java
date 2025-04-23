package test.model.mushroom;

import model.mushroom.MushroomStem;
import model.mushroom.Mushroomer;
import model.mushroom.spore.ParalyzingSpore;
import model.mushroom.spore.Spore;
import model.tecton.Tecton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MushroomStemTest {

    private Mushroomer mushroomer;
    private Tecton tecton;

    private MushroomStem stem;

    @BeforeEach
    void setup() {
        mushroomer = new Mushroomer();
        tecton = new Tecton();
        stem = new MushroomStem(mushroomer, tecton);
    }

    @Test
    void testHasThrown_1() {
        assertFalse(stem.hasThrown());
    }

    @Test
    void testHasThrown_2() {
        Tecton tecton2 = new Tecton();
        tecton2.addNeighbour(tecton);
        tecton.addNeighbour(tecton2);

        stem.throwSpore(tecton2);
        assertTrue(stem.hasThrown());
        stem.endTurn();
        assertFalse(stem.hasThrown());
    }

    @Test
    void testGetLevelUpCost() {
        assertEquals(3, stem.getLevelUpCost());
    }

    @Test
    void testThrowSpore_InRange() {
        Tecton tecton2 = new Tecton();
        tecton2.addNeighbour(tecton);
        tecton.addNeighbour(tecton2);

        assertTrue(stem.throwSpore(tecton2));
        assertEquals(1, stem.getNumThrownSpores());
    }

    @Test
    void testThrowSpore_OutOfRange() {
        Tecton tecton2 = new Tecton();
        Tecton tecton3 = new Tecton();

        tecton2.addNeighbour(tecton);
        tecton.addNeighbour(tecton2);

        tecton2.addNeighbour(tecton3);
        tecton3.addNeighbour(tecton);

        assertFalse(stem.throwSpore(tecton3));
        assertEquals(0, stem.getNumThrownSpores());
    }

    @Test
    void testThrowSpore_Thrown() {
        Tecton tecton2 = new Tecton();
        tecton2.addNeighbour(tecton);
        tecton.addNeighbour(tecton2);

        assertTrue(stem.throwSpore(tecton2));
        assertFalse(stem.throwSpore(tecton2));
        assertEquals(1, stem.getNumThrownSpores());
    }

    @Test
    void testLevelUp_Success() {
        Tecton tecton2 = new Tecton();
        tecton2.addNeighbour(tecton);
        tecton.addNeighbour(tecton2);

        for(int i=0; i<stem.getLevelUpCost(); i++){
            Spore s=new ParalyzingSpore(mushroomer, tecton);
            tecton.add(s);
            mushroomer.add(s);
        }

        assertTrue(stem.levelUp());
        assertEquals(1, stem.getLevel());
    }

    @Test
    void testLevelUp_Failure() {
        Tecton tecton2 = new Tecton();
        tecton2.addNeighbour(tecton);
        tecton.addNeighbour(tecton2);

        for(int i=0; i<stem.getLevelUpCost()-1; i++){
            Spore s=new ParalyzingSpore(mushroomer, tecton);
            tecton.add(s);
            mushroomer.add(s);
        }

        assertFalse(stem.levelUp());
        assertEquals(0, stem.getLevel());
    }

    @Test
    void testLevelUp_Failure2() {
        Tecton tecton2 = new Tecton();
        tecton2.addNeighbour(tecton);
        tecton.addNeighbour(tecton2);

        assertFalse(stem.levelUp());
        assertEquals(0, stem.getLevel());
    }

    @Test
    void testRemove() {
        Tecton tecton2 = new Tecton();
        tecton2.addNeighbour(tecton);
        tecton.addNeighbour(tecton2);

        for (int i = 0; i < stem.getMaxSporeThrows(); i++) {
            assertTrue(stem.throwSpore(tecton2));
            stem.endTurn();
        }

        assertFalse(mushroomer.getStems().contains(stem));
        assertFalse(tecton.getStems().contains(stem));
    }
    
}
