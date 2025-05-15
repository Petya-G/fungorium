package view.game.buttons;

import controller.Action;
import controller.Controller;
import model.insect.Insect;
import model.mushroom.MushroomThread;
import view.game.popup.InsectPop;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InsectButton extends GameButton {
    public Insect insect;

    public InsectButton(Insect insect, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.insect = insect;

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    InsectPop popupPanel = new InsectPop(insect);
                    JPopupMenu popupMenu = new JPopupMenu();
                    popupMenu.add(popupPanel);

                    popupMenu.show(InsectButton.this, InsectButton.this.getWidth(), -10);
                }

                if (SwingUtilities.isLeftMouseButton(e) && Controller.getAction() == Action.EAT_INSECT) {
                    Controller.getGame().eat((MushroomThread) Controller.getSelected(), insect);
                }

                Controller.refreshView();
            }
        };

        addMouseListener(mouseAdapter);
    }
}