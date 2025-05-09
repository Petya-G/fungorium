package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import model.Game;
import view.View;

public class Controller{
    private Game game;
    public View view;

    

    public Controller(Game game, View view) {
        this.game = game;
        this.view = view;
    }

}
