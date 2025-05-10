package view.game;

import model.tecton.Tecton;

import javax.swing.*;

public class TectonButton extends JButton {
    static int size = 30;
    Tecton tecton;
    ImageIcon imageIcon;

    public TectonButton(Tecton tecton, ImageIcon imageIcon) {
        super(imageIcon);
        setBounds(100, 100, size, size);
        setLocation((int)tecton.getPosX(), (int)tecton.getPosY());
    }
}
