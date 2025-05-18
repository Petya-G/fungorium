package view.game.buttons;

import controller.Action;
import controller.Controller;
import model.Game;
import model.insect.Insect;
import model.mushroom.spore.Spore;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A {@code SporeButton} osztály egy gombot reprezentál, amely egy spórát (Spore) jelenít meg a játékban.
 *
 * <p>Viselkedés:</p>
 * <ul>
 *     <li>Bal egérgomb: ha az aktuális akció {@code EAT}, akkor megpróbálja a spórát elfogyasztani
 *         a kiválasztott rovarral.</li>
 * </ul>
 */
public class SporeButton extends GameButton {

    /** A gombhoz tartozó spóra objektum. */
    public Spore spore;

    /** A játék példánya, amelyhez a spóra tartozik. */
    Game game;

    /**
     * Új {@code SporeButton} példány létrehozása egy adott spórával, képpel és szülő panellel.
     *
     * @param spore a megjelenítendő spóra
     * @param imageIcon a gomb ikonja
     * @param parent a szülő panel, amely tartalmazza ezt a gombot
     */
    public SporeButton(Spore spore, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.spore = spore;
        this.game = Controller.getGame();

        // Egérfigyelő hozzáadása az interaktív eseményekhez
        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                // Bal kattintás: spóra elfogyasztása rovar által, ha az akció EAT
                if (SwingUtilities.isLeftMouseButton(e) && Controller.getAction() == Action.EAT) {
                    if (Controller.getGame().eat((Insect) Controller.getSelected(), spore)) {
                        Controller.log("spore successfully eaten");
                    } else {
                        Controller.log("unable to eat spore");
                    }
                    Controller.setAction(Action.NONE);
                }

                // GUI frissítése az interakció után
                Controller.refreshView();
            }
        };

        addMouseListener(mouseAdapter);
    }
}
