package controller;

import model.Game;
import view.View;

public class Controller {
    private Game game;
    private View view;

    public Controller(Game game, View view) {
        this.game = game;
        this.view = view;
    }


}
