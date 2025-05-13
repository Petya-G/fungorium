package controller;

import model.Game;
import view.View;
import view.game.MapPanel;

public class Controller {
    public static Game game;
    private static Controller instance;
    public MapPanel mapPanel;
    public View view;

    public Controller() {
        game = new Game();
        this.mapPanel = new MapPanel(game);
        this.view = new View(game, mapPanel);
    }

    public static Controller getInstance() {
        if (instance == null) instance = new Controller();

        return instance;
    }

    public static void startGame(int playerCount) {
        game.startGame(playerCount);
    }

    public static View getView() {
        return instance.view;
    }

    public static MapPanel getMapPanel() {
        return instance.mapPanel;
    }
}
