package view.game.buttons;

import controller.Action;
import controller.Controller;
import model.Game;
import model.insect.Insect;
import model.mushroom.MushroomThread;
import view.game.popup.ThreadPop;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A {@code MushroomThreadButton} osztály egy gombot reprezentál, amely egy gombaszálat (MushroomThread) jelenít meg a játékban.
 *
 * <p>Viselkedés:</p>
 * <ul>
 *     <li>Jobb egérgomb: ha a szál tulajdonosa a jelenlegi játékos, megjelenít egy információs panelt (popup).</li>
 *     <li>Bal egérgomb: ha az aktuális akció {@code CUT}, akkor megpróbálja elvágni a szálat egy kiválasztott rovarral.</li>
 * </ul>
 */
public class MushroomThreadButton extends GameButton {

    /** A gombhoz tartozó gombaszál objektum. */
    public MushroomThread thread;

    /** A játék példány, amelyhez tartozik. */
    Game game;

    /**
     * Új {@code MushroomThreadButton} példány létrehozása.
     *
     * @param thread a megjelenítendő gombaszál
     * @param imageIcon a gomb ikonjául szolgáló kép
     * @param parent a szülő panel, amely tartalmazza a gombot
     */
    public MushroomThreadButton(MushroomThread thread, ImageIcon imageIcon, JPanel parent) {
        super(imageIcon, parent);
        this.thread = thread;
        this.game = Controller.getGame();

        // Egérfigyelő hozzáadása az események kezeléséhez
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                // Jobb egérgomb: popup megjelenítése, ha a szál a játékosé
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (game.getCurrentPlayer().equals(thread.getOwner())) {
                        ThreadPop popupPanel = new ThreadPop(thread);
                        JPopupMenu popupMenu = new JPopupMenu();
                        popupMenu.add(popupPanel);
                        popupMenu.show(MushroomThreadButton.this, MushroomThreadButton.this.getWidth(), -10);
                    }
                }

                // Bal egérgomb: szál elvágása, ha az aktuális akció CUT
                if (SwingUtilities.isLeftMouseButton(e) && Controller.getAction() == Action.CUT) {
                    if (Controller.getGame().cut((Insect) Controller.getSelected(), thread)) {
                        Controller.log("thread successfully cut");
                    } else {
                        Controller.log("unable to cut thread");
                    }
                    Controller.setAction(Action.NONE);
                }

                // GUI frissítése
                Controller.refreshView();
            }
        };

        addMouseListener(mouseAdapter);
    }
}
