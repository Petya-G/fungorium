package view.game.buttons;

import controller.Action;
import controller.Controller;
import model.Game;
import model.insect.Insect;
import model.mushroom.MushroomThread;
import view.game.popup.ThreadPop;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MushroomThreadButton extends GameButton {
    public MushroomThread thread;
    Game game;

    public MushroomThreadButton(MushroomThread thread, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.thread = thread;
        this.game = Controller.getGame();

        MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (game.getCurrentPlayer().equals( thread.getOwner())) {
                        ThreadPop popupPanel = new ThreadPop(thread);
                        JPopupMenu popupMenu = new JPopupMenu();
                        popupMenu.add(popupPanel);
                        popupMenu.show(MushroomThreadButton.this, MushroomThreadButton.this.getWidth(), -10);
                    }
                }

                if (SwingUtilities.isLeftMouseButton(e) && Controller.getAction() == Action.CUT) {
                    Controller.getGame().cut((Insect) Controller.getSelected(), thread);
                    Controller.setAction(Action.NONE);
                }

                Controller.refreshView();
            }
        };

        addMouseListener(mouseAdapter);
    }
}
