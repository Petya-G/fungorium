package view.game;

import controller.visitor.DrawVisitor;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import model.Game;
import model.tecton.Tecton;
import view.game.buttons.TectonButton;
import view.game.contentpanel.ContentPanel;

/**
 * A {@code MapPanel} a játék vizuális térképe, ahol a Tecton objektumok (csomópontok) és azok kapcsolatai kerülnek megjelenítésre.
 *
 * <p>A panel a {@link DrawVisitor} segítségével kirajzolja a térképi kapcsolatokat, és {@link TectonButton} gombokat jelenít meg
 * az interaktív működéshez.</p>
 *
 * <p>A panel háttérszíne halvány szürke, és szabad elhelyezésű ({@code null} layout) gombokat használ.</p>
 */
public class MapPanel extends JPanel {

    /** A látogató objektum, amely felel a Tecton objektumok kirajzolásáért. */
    private final DrawVisitor drawVisitor = new DrawVisitor();

    /** A térképen elhelyezett Tecton gombok listája. */
    public List<TectonButton> tectonButtons = new ArrayList<>();

    /** A játék példánya, amelyhez ez a panel tartozik. */
    private Game game;

    /** A tartalmi panel, amely frissül a kiválasztott Tecton alapján. */
    private ContentPanel contentPanel;

    /**
     * Létrehozza a {@code MapPanel} példányt az adott játékkal.
     *
     * @param game a játék példány, amelyhez ez a térkép tartozik
     */
    public MapPanel(Game game) {
        this.game = game;
        setLayout(null); // Szabad pozicionálás
        setBackground(new Color(250, 250, 250)); // Világosszürke háttér
    }

    /**
     * Visszaadja az aktuálisan beállított tartalmi panelt.
     */
    public ContentPanel getTectonContentPanel() {
        return contentPanel;
    }

    /**
     * Beállítja a {@code ContentPanel} példányt, amely frissül, amikor egy Tecton gombra kattintanak.
     *
     * @param contentPanel a hozzárendelendő tartalom panel
     */
    public void setTectonContentPanel(ContentPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    /**
     * A komponens kirajzolása, beleértve a Tecton kapcsolatok és gombok frissítését.
     *
     * @param g a grafikus kontextus
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawVisitor.setParameters((Graphics2D) g, getSize());

        // Gombok helyének frissítése
        for (TectonButton b : tectonButtons) {
            b.refreshState();
        }

        // Térkép kirajzolása
        for (Tecton t : game.getMap().tectons) {
            t.accept(drawVisitor);
        }
    }

    /**
     * Új Tecton gomb hozzáadása a térképhez és a kattintási események regisztrálása.
     *
     * @param tectonButton a hozzáadandó gomb
     */
    public void addTectonButton(TectonButton tectonButton) {
        tectonButtons.add(tectonButton);
        add(tectonButton);
        tectonButton.addActionListener(contentPanel); // kattintásra frissíti a tartalom panelt
        revalidate();
        repaint();
    }

    /*
    // Opcionálisan használható metódus a térkép ürítésére
    public void clearMap() {
        contentPanel.clear();
        for (TectonButton b : tectonButtons) {
            remove(b);
        }
        tectonButtons.clear();
    }
    */

    /**
     * Új játékobjektum beállítása a térképhez.
     *
     * @param game az új játékpéldány
     */
    public void setGame(Game game) {
        this.game = game;
    }
}
