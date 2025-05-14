package view.game.buttons;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import controller.Controller;
import model.mushroom.MushroomStem;
import view.game.PopUps.MushroomPop;

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

                //TODO: megcsinálni, hogy csak akkor jelenjen meg ha gombász játszik és ha a saját gombájára kattint

                MushroomPop popupPanel = new MushroomPop();
                JPopupMenu popupMenu = new JPopupMenu();
                popupMenu.add(popupPanel);



                popupMenu.show(MushroomStemButton.this, MushroomStemButton.this.getWidth(), 0);
                Controller.getInstance().handleButtonPress(Controller.ButtonPressed.DEFAULT);

            }

        };

        addMouseListener(mouseAdapter);
    }
}
