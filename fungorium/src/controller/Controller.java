package controller;

import model.Game;
import view.View;
import view.game.MapPanel;

public class Controller {
    private static Controller instance;
    private final Game game;
    private final MapPanel mapPanel;
    private final View view;

    public Controller() {
        this.game = new Game();
        this.mapPanel = new MapPanel(game);
        this.view = new View(game, mapPanel);
    }

    public static Controller getInstance() {
        if (instance == null) instance = new Controller();

        return instance;
    }

    public static Game getGame() {
        return instance.game;
    }

    public static View getView() {
        return instance.view;
    }

    public static MapPanel getMapPanel() {
        return instance.mapPanel;
    }
}
