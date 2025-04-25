package test.controller;

import controller.Game;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

public class GameTest {
    private Game game = new Game();

    @Test
    public void hasCurrentTurnWithEntityTest() {
        // Placeholder test for hasCurrentTurn(Entity entity)
    }

    @Test
    public void hasCurrentTurnWithPlayerTest() {
        // Placeholder test for hasCurrentTurn(Player player)
    }

    @Test
    public void startGameTest() {
        game.startGame(2);
        System.out.println(game.toString());
    }

    @Test
    public void getCurrentPlayerTest() {
        // Placeholder test for getCurrentPlayer()
    }

    @Test
    public void getWinnersTest() {
        // Placeholder test for getWinners()
    }

    @Test
    public void moveTest() {
        // Placeholder test for move(Insect insect, Tecton location)
    }

    @Test
    public void eatTest() {
        // Placeholder test for eat(Insect insect, Spore spore)
    }

    @Test
    public void cutTest() {
        // Placeholder test for cut(Insect insect, MushroomThread mushroomThread)
    }

    @Test
    public void plantMushroomStemTest() {
        // Placeholder test for plantMushroomStem(Tecton location)
    }

    @Test
    public void throwSporeTest() {
        // Placeholder test for throwSpore(MushroomStem mushroomStem, Tecton location)
    }

    @Test
    public void growThreadTest() {
        // Placeholder test for growThread(Tecton location)
    }
}
