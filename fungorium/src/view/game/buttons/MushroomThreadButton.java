package view.game.buttons;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import model.mushroom.MushroomThread;
import view.game.PopUps.MushroomPop;
import view.game.PopUps.ThreadPop;

public class MushroomThreadButton extends GameButton {
    public MushroomThread thread;

    public MushroomThreadButton(MushroomThread thread, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.thread = thread;

        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point initialMousePoint;

            @Override
            public void mousePressed(MouseEvent e) {
                initialMousePoint = e.getPoint();
                //TODO: megcsinálni, hogy csak akkor jelenjen meg ha gombász játszik és ha a saját gombájára kattint

                ThreadPop popupPanel = new ThreadPop();
                JPopupMenu popupMenu = new JPopupMenu();
                popupMenu.add(popupPanel);

                //popupMenu.setPopupSize(popupPanel.getPreferredSize());

                popupMenu.show(MushroomThreadButton.this, MushroomThreadButton.this.getWidth(), -10);
            }

        };

        addMouseListener(mouseAdapter);
    }
}
