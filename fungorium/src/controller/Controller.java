package controller;

import model.Game;
import view.View;
import view.game.MapPanel;
import view.game.MiddlePanel;

public class Controller {
    private static Controller instance;
    public Game game;
    public MapPanel mapPanel;
    public View view;

    public Controller(Game game) {
        this.game = game;
        this.mapPanel = new MapPanel(game);
        view = new View(game, mapPanel);
    }

    public static Controller getInstance() {
        return instance;
    }

    public static void startGame(Game game,int playerCount) {
        instance = new Controller(game);
        game.startGame(playerCount);
    }
}
