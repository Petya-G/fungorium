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
    private MushroomStem stem1;
    private Tecton tecton;


    @BeforeEach
    void setup() {
        mushroomer = new Mushroomer();
        stem1 = new MushroomStem(mushroomer, new Tecton());
        tecton = new Tecton();
    }

    @Test
    void testAddAndGetSpores() {
        Spore spore = new ParalyzingSpore(mushroomer, tecton);
        assertTrue(mushroomer.add(spore));
        assertTrue(mushroomer.getSpores(tecton).contains(spore));
    }

    @Test
    void testRemoveStem() {
        MushroomStem stem = new MushroomStem(mushroomer, tecton);
        assertTrue(mushroomer.add(stem));
        assertTrue(mushroomer.remove(stem));
    }

    @Test
    void testPlantMushroomStem_EnoughSpores() {
        MushroomThread thread = new MushroomThread(mushroomer, tecton);
        tecton.add(thread);
        mushroomer.add(thread);

        for(int i=0; i<stem1.getCost(); i++){
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

        for(int i=0; i<stem1.getCost()-1; i++){
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

        for(int i=0; i<stem1.getCost(); i++){
            Spore s1=new ParalyzingSpore(mushroomer, tecton);
            tecton.add(s1);
            mushroomer.add(s1);
        }

        assertFalse(mushroomer.plantMushroomStem(tecton));
    }

    @Test
    void testPlantMushroomStem_NoThread() {
        for(int i=0; i<stem1.getCost(); i++){
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

    /*
    @Test
    void testGrowMushroomThread_AlreadyGrown() {
        MushroomStem stem = new MushroomStem(mushroomer, tecton);
        tecton.add(stem);
        mushroomer.add(stem);

        for(int i=0; i<stem1.getCost(); i++){}

        DummyTecton neighbor = new DummyTecton();
        tecton.getNeighbours().add(neighbor);

        assertTrue(mushroomer.growMushroomThread(neighbor));
        assertFalse(neighbor.getThreads().isEmpty());
    }
    */

    /*
    @Test
    void testEat_ParalyzedInsect() {
        DummyInsect insect = new DummyInsect(true);
        MushroomThread thread = new MushroomThread(mushroomer, tecton);

        tecton.add(thread);
        tecton.add(insect);

        assertTrue(mushroomer.eat(thread, insect));
        assertTrue(tecton.getInsects().isEmpty());
    }

    @Test
    void testEat_NotParalyzedInsect() {
        DummyInsect insect = new DummyInsect(false);
        MushroomThread thread = new MushroomThread(mushroomer, tecton);
        tecton.add(thread);
        tecton.add(insect);

        assertFalse(mushroomer.eat(thread, insect));
        assertFalse(thread.hasEaten());
    }

    @Test
    void testLevelUpSuccess() {
        MushroomStem stem = new MushroomStem(mushroomer, tecton);
        DummySpore spore = new DummySpore(mushroomer, tecton);

        tecton.add(stem);
        tecton.add(spore);
        mushroomer.add(stem);
        mushroomer.add(spore);

        assertTrue(mushroomer.levelUp(stem));
    }

    @Test
    void testLevelUpFail_NoSpores() {
        MushroomStem stem = new MushroomStem(mushroomer, tecton);
        tecton.add(stem);
        mushroomer.add(stem);

        assertFalse(mushroomer.levelUp(stem));
    }

    @Test
    void testEndTurn_ClearsSporeThrows() {
        MushroomStem stem = new MushroomStem(mushroomer, tecton);
        tecton.add(stem);
        mushroomer.add(stem);

        // Verify initial state
        stem.throwSpore(new DummyTecton(0));
        assertEquals(1, stem.getNumThrownSpores());

        // Reset and verify
        mushroomer.endTurn();
        assertEquals(0, stem.getNumThrownSpores());
    }

    // --- Enhanced Dummy Classes ---

    static class DummyTecton extends Tecton {
        private final List<Tecton> neighbours = new ArrayList<>();
        private final List<MushroomStem> stems = new ArrayList<>();
        private final List<MushroomThread> threads = new ArrayList<>();
        private final List<Insect> insects = new ArrayList<>();
        private int mockDistance = 0;

        public DummyTecton() {
            super();
        }

        public DummyTecton(int dist) {
            super();
            this.mockDistance = dist;
        }

        @Override
        public List<Tecton> getNeighbours() {
            return neighbours;
        }

        @Override
        public int distanceTo(Tecton target) {
            return mockDistance;
        }

        @Override
        public boolean hasStem() {
            return !stems.isEmpty();
        }

        @Override
        public boolean add(MushroomStem stem) {
            if (hasStem()) return false;
            return stems.add(stem);
        }

        @Override
        public boolean add(MushroomThread thread) {
            return threads.add(thread);
        }

        @Override
        public boolean add(Insect insect) {
            return insects.add(insect);
        }

        @Override
        public List<MushroomStem> getStems() {
            return stems;
        }

        @Override
        public List<MushroomThread> getThreads() {
            return threads;
        }

        @Override
        public List<Insect> getInsects() {
            return insects;
        }
    }

    static class DummySpore extends Spore {
        public DummySpore(Mushroomer owner, Tecton location) {
            super(owner, location);
        }

        @Override
        public void remove() {
            Mushroomer owner = (Mushroomer) getOwner();
            owner.getSpores(getLocation()).remove(this);
        }
    }
    */
}