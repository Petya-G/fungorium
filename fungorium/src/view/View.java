package view;

import model.Game;
import view.game.GamePanel;
import view.game.MapPanel;
import view.game.contentpanel.ContentPanel;
import view.game.winner.WinnerView;
import view.mainMenu.MainMenu;

import javax.swing.*;
import java.awt.*;

/**
 * A {@code View} osztály a játék főablaka (fő {@link JFrame}), amely kezeli a különböző nézeteket,
 * például a főmenüt, a játékképernyőt és a győztesek kijelzését.
 *
 * <p>Ez az osztály felelős a nézetek közötti váltásért a {@link CardLayout} segítségével, valamint
 * az ablak beállításáért és megjelenítéséért.</p>
 */
public class View extends JFrame {

    /** Kártyás elrendezés a nézetek közötti váltáshoz. */
    CardLayout cardLayout = new CardLayout();

    /** A panel, amely tartalmazza az összes nézetet. */
    JPanel cardPanel = new JPanel(cardLayout);

    /** A főmenü nézet. */
    MainMenu mainMenu;

    /** A játék nézet (játékfelület). */
    GamePanel gamePanel;

    /** A játéktér panel (Tecton gombokkal és kapcsolatokkal). */
    MapPanel mapPanel;

    /** A győzteseket megjelenítő nézet. */
    WinnerView winnerView;

    /**
     * Létrehozza a fő ablakot, inicializálja a nézeteket és megjeleníti a főmenüt.
     *
     * @param game a játék logikai modellje
     * @param mapPanel a térképet tartalmazó panel
     * @param contentPanel a részletes információkat megjelenítő panel
     */
    public View(Game game, MapPanel mapPanel, ContentPanel contentPanel) {
        super("Fungorium");

        // Nézetek inicializálása
        gamePanel = new GamePanel(game, mapPanel, contentPanel);
        this.mainMenu = new MainMenu();
        this.mapPanel = mapPanel;
        winnerView = new WinnerView();

        // Ablak beállítások
        setIconImage(ImageManager.getIcon("mushroom_stem_3").getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null); // képernyő közepére
        setResizable(false);
        setLayout(new BorderLayout());

        // Nézetek hozzáadása a kártyapanelhez
        cardPanel.add("mainMenu", mainMenu);
        cardPanel.add("gameView", gamePanel);
        cardPanel.add("winnerView", winnerView);

        add(cardPanel, BorderLayout.CENTER);
        setVisible(true);

        // Alapértelmezett nézet
        showPanel("mainMenu");
    }

    /**
     * Nézet (panel) megjelenítése a megadott név alapján.
     *
     * @param name a megjelenítendő nézet neve (pl. "mainMenu", "gameView", "winnerView")
     */
    public void showPanel(String name) {
        System.out.println("showing panel " + name);
        cardLayout.show(cardPanel, name);
    }
}
