package view.game.buttons;

import controller.Action;
import controller.Controller;
import model.Game;
import model.insect.Insect;
import model.mushroom.spore.Spore;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SporeButton extends GameButton {
    public Spore spore;
    Game game;

    public SporeButton(Spore spore, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.spore = spore;
        this.game = Controller.getGame();

        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                
                if (SwingUtilities.isLeftMouseButton(e) && Controller.getAction() == Action.EAT) {
                    if (Controller.getGame().eat((Insect) Controller.getSelected(), spore)) {
                        Controller.log("spore successfully eaten");
                    } else {
                        Controller.log("unable to eat spore");
                    }
                    Controller.setAction(Action.NONE);
                }
                

                Controller.refreshView();
            }
        };

        addMouseListener(mouseAdapter);
    }
}