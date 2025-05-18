package view.game.buttons;

import controller.Action;
import controller.Controller;
import model.Game;
import model.insect.Insect;
import model.mushroom.MushroomStem;
import model.tecton.Tecton;
import view.game.popup.MushroomStemPop;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MushroomStemButton extends GameButton {
    public MushroomStem stem;
    Game game;

    public MushroomStemButton(MushroomStem stem, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.stem = stem;
        this.game = Controller.getGame();

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (game.getCurrentPlayer().equals(stem.getOwner())){
                        MushroomStemPop popupPanel = new MushroomStemPop(stem);
                        JPopupMenu popupMenu = new JPopupMenu();
                        popupMenu.add(popupPanel);
                        popupMenu.show(MushroomStemButton.this, MushroomStemButton.this.getWidth(), -10);
                    }
                }

                if (SwingUtilities.isLeftMouseButton(e) && Controller.getAction() == Action.THROW_SPORE) {
                    if (Controller.getGame().throwSpore(stem, (Tecton)Controller.getSelected())) {
                        Controller.log("spore succesfully thrown");
                    } else {
                        Controller.log("unable to throw spore");
                    }
                    Controller.setAction(Action.NONE);
                }

                Controller.refreshView();
            }
        };

        addMouseListener(mouseAdapter);
    }
}