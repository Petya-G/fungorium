package test.model.tecton;

import controller.Game;
import model.mushroom.MushroomStem;
import model.mushroom.Mushroomer;
import model.tecton.StemlessTecton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class StemlessTectonTest {
    private StemlessTecton tecton = new StemlessTecton();
    private Mushroomer mushroomer = new Mushroomer();

    @Test
    public void addStemTest() {
        Game.random.setSeed(1);

        assertFalse(tecton.add(new MushroomStem(mushroomer, tecton)));
        assert (tecton.getStems().isEmpty());
    }
}
