package view.game.buttons;

import controller.Action;
import controller.Controller;
import model.insect.Insect;
import model.mushroom.spore.Spore;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SporeButton extends GameButton {
    public Spore spore;

    public SporeButton(Spore spore, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.spore = spore;

        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && Controller.getAction() == Action.EAT) {
                    Controller.getGame().eat((Insect) Controller.getSelected(), spore);
                }
            }
        };

        addMouseListener(mouseAdapter);
    }
}