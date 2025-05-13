package view.game.buttons;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.mushroom.MushroomStem;

public class MushroomStemButton extends GameButton {
    public MushroomStem stem;

    public MushroomStemButton(MushroomStem stem, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.stem = stem;

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
