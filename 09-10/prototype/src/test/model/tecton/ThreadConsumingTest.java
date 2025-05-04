package test.model.tecton;

import controller.Game;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.tecton.ThreadConsumingTecton;
import org.junit.jupiter.api.Test;

public class ThreadConsumingTest {

    private ThreadConsumingTecton tecton = new ThreadConsumingTecton();
    private Mushroomer mushroomer = new Mushroomer();

    @Test
    public void endRoundTest() {
        Game.random.setSeed(1);

        tecton.add(new MushroomThread(mushroomer, tecton));
        tecton.add(new MushroomThread(mushroomer, tecton));
        tecton.add(new MushroomThread(mushroomer, tecton));
        tecton.add(new MushroomThread(mushroomer, tecton));

        tecton.endRound();
        assert (tecton.getThreads().isEmpty());
    }
}
