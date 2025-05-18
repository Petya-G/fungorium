package view.game;

import javax.swing.*;

import controller.Controller;
import model.Game;
import model.insect.Insecter;
import model.mushroom.Mushroomer;
import view.IUpdateGUI;
import view.ImageManager;

import java.awt.*;

/**
 * A {@code TurnOrderPanel} osztály megjeleníti az aktuális kör számát és a soron következő játékos nevét,
 * valamint a játékost reprezentáló ikont.
 *
 * <p>A panel automatikusan frissül a körök váltásakor a {@link Controller#subscribeGUIUpdate} mechanizmus segítségével.</p>
 */
public class TurnOrderPanel extends JPanel implements IUpdateGUI {

    /** A játékos nevét és a kör számát tartalmazó szöveges label. */
    JLabel gameInfoLabel;

    /** A játékos típusához tartozó ikon (pl. gomba, rovar). */
    JLabel img;

    /**
     * Létrehozza a {@code TurnOrderPanel} példányt, és előkészíti a GUI elemeket.
     */
    public TurnOrderPanel() {
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);

        // Szöveges információ a játékról
        gameInfoLabel = new JLabel("Game Information");
        gameInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gameInfoLabel.setForeground(new Color(60, 60, 60)); // Sötétszürke szöveg
        add(gameInfoLabel);

        // Ikonlabel előkészítése
        img = new JLabel(new ImageIcon());
        add(img);

        // Frissítésre való feliratkozás
        Controller.subscribeGUIUpdate(this);
    }

    /**
     * Frissíti a panelen megjelenő adatokat az aktuális játékállapot alapján.
     * Megjeleníti a jelenlegi játékos nevét, körszámot és típusához illő ikont.
     */
    @Override
    public void GUIupdate() {
        Game game = Controller.getGame();
        if (game.getCurrentPlayer() == null)
            return;

        String text = "[turn " + game.getTurn() + "] " + game.getCurrentPlayer().getName();
        ImageIcon ii;

        // Játékos típusa alapján ikon választás
        if (game.getCurrentPlayer().getClass() == Mushroomer.class) {
            int playerId = ((Mushroomer) game.getCurrentPlayer()).getShroomerID();
            ii = new ImageIcon(ImageManager.getIcon("mushroom_stem_" + ((playerId) % 4 + 1))
                    .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        } else {
            int playerId = ((Insecter) game.getCurrentPlayer()).getInsecterID();
            String p = "normal_";
            ii = new ImageIcon(ImageManager.getIcon("insect_" + p + ((playerId + 1) % 4 + 1))
                    .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        }

        gameInfoLabel.setText(text);
        img.setIcon(ii);
    }
}
