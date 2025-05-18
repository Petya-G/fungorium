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

/**
 * A {@code MushroomStemButton} osztály egy gombot reprezentál, amely egy gombatörzshöz (MushroomStem) kapcsolódik.
 *
 * <p>Viselkedés:</p>
 * <ul>
 *     <li>Jobb egérgomb: megjelenít egy információs panelt, ha a törzs a jelenlegi játékosé.</li>
 *     <li>Bal egérgomb: ha az aktuális akció {@code THROW_SPORE}, akkor megkísérli spórát dobni
 *         a kiválasztott tectonra.</li>
 * </ul>
 */
public class MushroomStemButton extends GameButton {

    /** A gombhoz tartozó gombatörzs objektum. */
    public MushroomStem stem;

    /** A játék példány, amelyhez tartozik. */
    Game game;

    /**
     * Új {@code MushroomStemButton} létrehozása.
     *
     * @param stem a megjelenítendő gombatörzs
     * @param imageIcon a gomb ikonjául szolgáló kép
     * @param parent a szülő panel, amely tartalmazza a gombot
     */
    public MushroomStemButton(MushroomStem stem, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.stem = stem;
        this.game = Controller.getGame();

        // Egérfigyelő hozzáadása az események kezeléséhez
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Jobb kattintás: popup nyitása a saját gombatörzshöz
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (game.getCurrentPlayer().equals(stem.getOwner())) {
                        MushroomStemPop popupPanel = new MushroomStemPop(stem);
                        JPopupMenu popupMenu = new JPopupMenu();
                        popupMenu.add(popupPanel);
                        popupMenu.show(MushroomStemButton.this, MushroomStemButton.this.getWidth(), -10);
                    }
                }

                // Bal kattintás: spóra dobása a kiválasztott tectonra
                if (SwingUtilities.isLeftMouseButton(e) && Controller.getAction() == Action.THROW_SPORE) {
                    if (Controller.getGame().throwSpore(stem, (Tecton) Controller.getSelected())) {
                        Controller.log("spore succesfully thrown");
                    } else {
                        Controller.log("unable to throw spore");
                    }
                    Controller.setAction(Action.NONE);
                }

                // GUI frissítése minden kattintás után
                Controller.refreshView();
            }
        };

        addMouseListener(mouseAdapter);
    }
}
