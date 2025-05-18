package view.game;

import model.Game;
import view.game.contentpanel.ContentPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * A {@code MiddlePanel} a játékfelület központi része, amely három fő elemet tartalmaz:
 * <ul>
 *     <li>{@link TurnOrderPanel} – a játékosok sorrendjének kijelzése felül</li>
 *     <li>{@link MapPanel} – a játéktér megjelenítése középen</li>
 *     <li>{@link ContentPanel} – a kiválasztott objektum részletes adatai alul</li>
 * </ul>
 *
 * <p>A panel {@code BorderLayout}-ot használ az elemek elrendezésére, és világos háttérrel rendelkezik.</p>
 */
public class MiddlePanel extends JPanel {

    /** A játékosok sorrendjét mutató panel. */
    TurnOrderPanel turnOrderPanel = new TurnOrderPanel();

    /** A játéktér (Tecton gombok és kapcsolatok megjelenítése). */
    MapPanel mapPanel;

    /** Az éppen kijelölt objektum részleteit megjelenítő panel. */
    ContentPanel contentPanel;

    /** A játék logikai modellje. */
    Game game;

    /**
     * Létrehozza a {@code MiddlePanel} példányt és elhelyezi a játék részelemeit.
     *
     * @param game a játék példány
     * @param mapPanel a játéktér megjelenítő panel
     * @param contentPanel a kiválasztott objektum adatait tartalmazó panel
     */
    public MiddlePanel(Game game, MapPanel mapPanel, ContentPanel contentPanel) {
        this.game = game;
        this.mapPanel = mapPanel;
        this.contentPanel = contentPanel;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(createTitledBorder());

        // Felső rész: játékosok sorrendje
        add(turnOrderPanel, BorderLayout.NORTH);

        // Középső rész: játéktér
        add(mapPanel, BorderLayout.CENTER);

        // Alsó rész: részletező panel
        add(contentPanel, BorderLayout.SOUTH);

        // Tecton gombok kattintására frissítse a tartalom panelt
        mapPanel.setTectonContentPanel(contentPanel);
    }

    /**
     * Címkézett szegély létrehozása a panel köré.
     *
     * @return a létrehozott {@code Border}
     */
    private Border createTitledBorder() {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
                "Game Area"
        );
        titledBorder.setTitleColor(Color.BLACK);
        return titledBorder;
    }
}
