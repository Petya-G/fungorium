package controller;

import model.Game;
import view.View;
import view.game.MapPanel;
import view.game.MiddlePanel;
import view.mainMenu.MainMenu;

import javax.swing.*;

public class Controller {
    private static Controller instance;
    public Game game;
    public MapPanel mapPanel;
    public View view;

    public Controller() {
        instance = this;
        this.game = new Game();

        this.mapPanel = new MapPanel(game);
        view = new View(game, mapPanel);

    }

    /*public Controller(Game game) {
        this.game = game;

        this.mapPanel = new MapPanel(game);
        view = new View(game, mapPanel);

    }*/

    public static Controller getInstance() {
        return instance;
    }

    public void startGame(int playerCount) {
        game.startGame(playerCount);
    }

    public View getView() {
        return view;
    }

}
