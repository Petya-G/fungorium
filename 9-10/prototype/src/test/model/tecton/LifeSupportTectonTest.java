package test.model.tecton;

import controller.Game;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.tecton.LifeSupportTecton;
import org.junit.jupiter.api.Test;

public class LifeSupportTectonTest {

    private LifeSupportTecton tecton = new LifeSupportTecton();
    private Mushroomer mushroomer = new Mushroomer();

    @Test
    public void removeUnconnectedThreadsTest() {
        Game.random.setSeed(1);

        tecton.add(new MushroomThread(mushroomer, tecton));
        tecton.add(new MushroomThread(mushroomer, tecton));
        tecton.add(new MushroomThread(mushroomer, tecton));
        tecton.add(new MushroomThread(mushroomer, tecton));

        tecton.removeUnconnectedThreads();
        assert (tecton.getThreads().size() == 4);
    }
}
