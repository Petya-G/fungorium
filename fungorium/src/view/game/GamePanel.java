package view.game;

import model.Game;
import view.game.sidebar.SidebarPanel;
import view.game.contentpanel.ContentPanel;

import javax.swing.*;
import java.awt.*;

/**
 * A {@code GamePanel} a játék főképernyőjének egyik legfelső szintű panelje,
 * amely három fő részegység összehangolt elrendezését biztosítja:
 * <ul>
 *     <li>{@link SidebarPanel} – a bal oldalon található vezérlők és napló</li>
 *     <li>{@link MiddlePanel} – a játéktér és a játékmenet tartalma</li>
 * </ul>
 *
 * <p>Ez a panel felelős az összes vizuális komponens egységes elrendezéséért a játék futása során.</p>
 */
public class GamePanel extends JPanel {

    /** Az oldalsáv (gombok, chatlog). */
    SidebarPanel sidebarPanel = new SidebarPanel();

    /** A játéktér és információs panelek középső része. */
    MiddlePanel middlePanel;

    /** A játék aktuális állapotát kezelő objektum. */
    Game game;

    /** A játéktér (térkép) panel. */
    MapPanel mapPanel;

    /** A kijelölt objektumhoz tartozó információkat megjelenítő panel. */
    ContentPanel contentPanel;

    /**
     * Létrehozza a {@code GamePanel} példányt és inicializálja az összes alpanelt.
     *
     * @param game a játék példány, amelyet ez a panel kezel
     * @param mapPanel a játéktér (térkép) panel
     * @param contentPanel a kiválasztott objektumhoz tartozó tartalompanel
     */
    public GamePanel(Game game, MapPanel mapPanel, ContentPanel contentPanel) {
        this.game = game;
        this.middlePanel = new MiddlePanel(game, mapPanel, contentPanel);
        this.mapPanel = mapPanel;
        this.contentPanel = contentPanel;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Bal oldalra: vezérlőgombok és chat
        add(sidebarPanel, BorderLayout.WEST);

        // Középre: térkép és kapcsolódó tartalmak
        add(middlePanel, BorderLayout.CENTER);
    }
}
