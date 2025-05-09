package controller;

import model.Game;
import model.tecton.*;
import view.View;

public class Controller {
    private static Controller instance;
    public View view;
    public Game game;

    public Controller(Game game, View view) {
        this.game = game;
        this.view = view;
    }

    public static Controller getInstance() {
        return instance;
    }

    public static void startGame(Game game, View view) {
        instance = new Controller(game, view);
        game.startGame(4);
    }
}
