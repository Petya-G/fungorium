package view.game.buttons;

import controller.Action;
import controller.Controller;
import model.insect.Insect;
import model.mushroom.MushroomStem;
import model.tecton.Tecton;
import view.game.popup.MushroomStemPop;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MushroomStemButton extends GameButton {
    public MushroomStem stem;

    public MushroomStemButton(MushroomStem stem, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.stem = stem;

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    MushroomStemPop popupPanel = new MushroomStemPop(stem);
                    JPopupMenu popupMenu = new JPopupMenu();
                    popupMenu.add(popupPanel);
                    popupMenu.show(MushroomStemButton.this, MushroomStemButton.this.getWidth(), -10);
                }

                if (SwingUtilities.isLeftMouseButton(e) && Controller.getAction() == Action.THROW_SPORE) {
                    Controller.getGame().throwSpore(stem, (Tecton)Controller.getSelected());
                }
            }
        };

        addMouseListener(mouseAdapter);
    }
}