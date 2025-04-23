package test.model.mushroom;

import model.mushroom.MushroomStem;
import model.mushroom.Mushroomer;
import model.mushroom.spore.Spore;
import model.tecton.Tecton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MushroomStemTest {

    private DummyMushroomer mushroomer;
    private DummyTecton tecton;

    private MushroomStem stem;

    @BeforeEach
    void setup() {
        mushroomer = new DummyMushroomer();
        tecton = new DummyTecton();
        mushroomer.setSpores(Collections.emptyList());
        stem = new MushroomStem(mushroomer, tecton);
    }

    @Test
    void testGetCost() {
        assertEquals(3, stem.getCost());
    }

    @Test
    void testThrowSporeSuccess() {
        Tecton target = new DummyTecton(1); // in range
        assertTrue(stem.throwSpore(target));
        assertEquals(1, stem.getNumThrownSpores());
    }

    @Test
    void testThrowSporeFail_TooFar() {
        stem = new MushroomStem(mushroomer, new DummyTecton(10)); // <- fontos: forrás
        Tecton target = new Tecton(); // cél lehet sima is
        assertFalse(stem.throwSpore(target));
    }

    @Test
    void testThrowSporeFail_AlreadyThrown() {
        Tecton target = new DummyTecton(0);
        assertTrue(stem.throwSpore(target));
        assertFalse(stem.throwSpore(target));
    }

    @Test
    void testLevelUpSuccess() {
        Spore dummySpore = new DummySpore(mushroomer, tecton);
        mushroomer.setSpores(List.of(dummySpore));
        assertTrue(stem.levelUp());
    }

    @Test
    void testLevelUpFail_NoSpores() {
        mushroomer.setSpores(Collections.emptyList());
        assertFalse(stem.levelUp());
    }

    @Test
    void testEndTurn_ResetsThrown() {
        Tecton target = new DummyTecton(0);
        assertTrue(stem.throwSpore(target));
        stem.endTurn();
        assertTrue(stem.throwSpore(target));
    }

    @Test
    void testGetMaxSporeThrows() {
        assertEquals(5, stem.getMaxSporeThrows());
    }

    @Test
    void testRemove_CallsBackToOwnerAndLocation() {
        tecton.clearCalled = false;
        mushroomer.clearCalled = false;
        stem.remove();
        assertTrue(tecton.clearCalled);
        assertTrue(mushroomer.clearCalled);
    }

    static class DummyTecton extends Tecton {
        private int distance;
        public boolean clearCalled = false;

        public DummyTecton() {
            this.distance=0;
        }

        public DummyTecton(int distance) {
            this.distance = distance;
        }

        //nem keres valódi utat, csak visszaadja a distance-t
        @Override
        public int distanceTo(Tecton destination) {
            return distance;
        }

        @Override
        public boolean add(Spore sp) {
            return true;
        }

        @Override
        public boolean add(MushroomStem ms) {
            return true;
        }

        public boolean remove(MushroomStem ms) {
            clearCalled = true;
            return true;
        }
    }

    static class DummyMushroomer extends Mushroomer {
        private List<Spore> spores;
        public boolean clearCalled = false;

        public void setSpores(List<Spore> spores) {
            this.spores = spores;
        }

        @Override
        public List<Spore> getSpores(Tecton tecton) {
            return spores;
        }

        @Override
        public boolean remove(MushroomStem ms) {
            clearCalled = true;
            return true;
        }

        @Override
        public boolean add(Spore sp) {
            return true;
        }
    }

    static class DummySpore extends Spore {
        public DummySpore(DummyMushroomer owner, Tecton location) {
            super(owner, location);
        }

        @Override
        public void remove() {
            ((DummyMushroomer) getOwner()).setSpores(Collections.emptyList());
        }
    }
}
