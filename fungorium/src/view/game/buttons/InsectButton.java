package view.game.buttons;

import controller.Action;
import controller.Controller;
import model.Game;
import model.insect.Insect;
import model.mushroom.MushroomThread;
import view.game.popup.InsectPop;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InsectButton extends GameButton {
    public Insect insect;
    Game game;

    public InsectButton(Insect insect, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.insect = insect;
        this.game = Controller.getGame();

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    if(game.getCurrentPlayer() == insect.getOwner() && insect.getBaseSpeed() > 0) {
                        InsectPop popupPanel = new InsectPop(insect);
                        JPopupMenu popupMenu = new JPopupMenu();
                        popupMenu.add(popupPanel);

                        popupMenu.show(InsectButton.this, InsectButton.this.getWidth(), -10);
                    }
                }

                if (SwingUtilities.isLeftMouseButton(e) && Controller.getAction() == Action.EAT_INSECT) {
                    Controller.log("menu insect eat clicked");
                    Controller.getGame().eat((MushroomThread) Controller.getSelected(), insect);
                    Controller.setAction(Action.NONE);
                }
                
                Controller.refreshView();
            }
        };

        addMouseListener(mouseAdapter);
    }
}