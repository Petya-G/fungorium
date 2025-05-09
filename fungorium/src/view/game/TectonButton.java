package view.game;

import java.awt.Dimension;

import javax.swing.JButton;

import model.Game;
import model.tecton.Tecton;

public class TectonButton extends JButton {
    int coordX, coordY;
    static int size = 30;

    Tecton tecton;

    public TectonButton(Tecton tecton)
    {
        super();
        setBounds(100, 100, 20, 20);
        //setLocation(100, 100);
        coordX = 100;
        coordY = 202;

        //this.setPreferredSize(new Dimension(size, size));
        //this.setSize(size, size);


        this.tecton = tecton;
    }

}
