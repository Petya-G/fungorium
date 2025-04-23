package test.model.tecton;

import controller.Game;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.tecton.SingleThreadedTecton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SingleThreadedTectonTest {
    private SingleThreadedTecton tecton = new SingleThreadedTecton();
    private Mushroomer mushroomer = new Mushroomer();

    @Test
    public void addThreadTest() {
        Game.random.setSeed(1);

        assertTrue(tecton.add(new MushroomThread(mushroomer, tecton)));
        assertFalse(tecton.add(new MushroomThread(mushroomer, tecton)));
        assert (tecton.getThreads().size() == 1);
    }
}
