package view.game.buttons;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.mushroom.MushroomThread;

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
            }

        };

        addMouseListener(mouseAdapter);
    }
}
