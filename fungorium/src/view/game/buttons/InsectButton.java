package view.game.buttons;

import controller.Action;
import controller.Controller;
import model.Game;
import model.insect.Insect;
import model.mushroom.MushroomThread;
import view.game.popup.InsectPop;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Az {@code InsectButton} osztály a játékban megjelenített rovarokat reprezentáló gomb.
 *
 * A gomb ikonja a rovar tulajdonságai alapján jelenik meg, és különböző egérműveletekre reagál:
 * <ul>
 *   <li>Jobb kattintásra megnyit egy interaktív információs panelt (popup).</li>
 *   <li>Bal kattintásra végrehajtja az "EAT_INSECT" műveletet, ha aktív.</li>
 * </ul>
 */
public class InsectButton extends GameButton {

    /** A gombhoz tartozó rovar objektum. */
    public Insect insect;

    /** A játék példánya, amelyben a rovar létezik. */
    Game game;

    /**
     * Létrehoz egy új {@code InsectButton} példányt a megadott rovarral, képpel és szülő panellel.
     *
     * @param insect a reprezentált rovar
     * @param imageIcon a gomb megjelenítésére szolgáló képikon
     * @param parent a szülő panel, amelyhez a gomb tartozik
     */
    public InsectButton(Insect insect, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.insect = insect;
        this.game = Controller.getGame();

        // Egérfigyelő hozzáadása az eseménykezeléshez
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                // Jobb gombbal: popup megnyitása, ha a rovar a játékosé és képes mozogni
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (game.getCurrentPlayer() == insect.getOwner() && insect.getBaseSpeed() > 0) {
                        InsectPop popupPanel = new InsectPop(insect);
                        JPopupMenu popupMenu = new JPopupMenu();
                        popupMenu.add(popupPanel);
                        popupMenu.show(InsectButton.this, InsectButton.this.getWidth(), -10);
                    }
                }

                // Bal gombbal: rovar elfogyasztása, ha az aktuális akció EAT_INSECT
                if (SwingUtilities.isLeftMouseButton(e) && Controller.getAction() == Action.EAT_INSECT) {
                    if (Controller.getGame().eat((MushroomThread) Controller.getSelected(), insect)) {
                        Controller.log("insect successfully eaten");
                    } else {
                        Controller.log("unable to eat insect");
                    }
                    Controller.setAction(Action.NONE);
                }

                // GUI frissítése minden interakció után
                Controller.refreshView();
            }
        };

        addMouseListener(mouseAdapter);
    }
}
