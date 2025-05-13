package view.game.buttons;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.insect.Insect;

public class InsectButton extends GameButton {
    public Insect insect;

    public InsectButton(Insect insect, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.insect = insect;

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
