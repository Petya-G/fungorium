package test.model.mushroom;

import model.insect.Insect;
import model.insect.Insecter;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.tecton.Tecton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MushroomThreadTest {

    private DummyMushroomer mushroomer;
    private DummyTecton tecton;
    private MushroomThread thread;

    @BeforeEach
    void setup() {
        mushroomer = new DummyMushroomer();
        tecton = new DummyTecton();
        thread = new MushroomThread(mushroomer, tecton);
    }

    @Test
    void testHasEatenAndSetter() {
        assertFalse(thread.hasEaten());
        thread.setEaten(true);
        assertTrue(thread.hasEaten());
    }

    @Test
    void testSetCutOffAndEndTurn_RemovesAfter2Turns() {
        DummyTecton dummyTecton = new DummyTecton();
        MushroomThread cutThread = new MushroomThread(mushroomer, dummyTecton);

        cutThread.setCutoff(true);
        cutThread.endTurn();
        assertFalse(dummyTecton.removeCalled);

        cutThread.endTurn();
        assertTrue(dummyTecton.removeCalled);
    }

    @Test
    void testEat_ParalyzedInsect() {
        Insect insect = new DummyInsect(true);
        assertTrue(thread.eat(insect));
        assertTrue(thread.hasEaten());
    }

    @Test
    void testEat_NotParalyzedInsect() {
        Insect insect = new DummyInsect(false);
        assertFalse(thread.eat(insect));
        assertFalse(thread.hasEaten());
    }

    @Test
    void testIsConnected_WhenStemPresent() {
        tecton.setHasStem(true);
        assertTrue(thread.isConnected());
    }

    @Test
    void testIsConnected_WhenNoStemAndNoNeighbours() {
        tecton.setHasStem(false);
        tecton.getNeighbours().clear();
        assertFalse(thread.isConnected());
    }

    @Test
    void testHasValidThreadReturnsTrue() {
        MushroomThread mt = new MushroomThread(mushroomer, tecton);
        tecton.add(mt);
        assertTrue(mt.hasValidThread(tecton));
    }

    static class DummyTecton extends Tecton {
        public boolean removeCalled = false;
        private boolean hasStem = false;

        public void setHasStem(boolean hasStem) {
            this.hasStem = hasStem;
        }

        @Override
        public boolean hasStem() {
            return hasStem;
        }

        @Override
        public boolean remove(MushroomThread th) {
            removeCalled = true;
            return true;
        }
    }

    static class DummyMushroomer extends Mushroomer {}

    static class DummyInsecter extends Insecter {}

    static class DummyInsect extends Insect {
        private final boolean paralyzed;

        public DummyInsect(boolean paralyzed) {
            super(new DummyInsecter(), new Tecton());
            this.paralyzed = paralyzed;
        }

        @Override
        public boolean isParalyzed() {
            return paralyzed;
        }

        @Override
        public void remove() {

        }
    }
}
