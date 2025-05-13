package view.game.buttons;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.mushroom.spore.Spore;

public class SporeButton extends GameButton {
    public Spore spore;

    public SporeButton(Spore spore, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.spore = spore;

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
