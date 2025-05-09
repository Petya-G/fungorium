package view.game;

import model.tecton.Tecton;

import javax.swing.*;

public class TectonButton extends JButton {
    static int size = 30;
    int coordX, coordY;
    Tecton tecton;

    public TectonButton(Tecton tecton) {
        super();
        setBounds(100, 100, size, size);
        //setLocation(100, 100);
        coordX = 100;
        coordY = 202;

        this.tecton = tecton;
    }
}
