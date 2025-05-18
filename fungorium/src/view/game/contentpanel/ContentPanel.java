package view.game.contentpanel;

import model.tecton.Tecton;
import view.IUpdateGUI;
import view.game.buttons.TectonButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A {@code ContentPanel} a játék jobb oldali részén megjelenő panel,
 * amely a kiválasztott Tectonhoz tartozó részleteket (szálak, spórák, törzsek, rovarok) mutatja.
 *
 * <p>Részei négy külön panelre oszlanak: {@code StemsPanel}, {@code SporesPanel}, {@code ThreadsPanel}, {@code InsectsPanel}.</p>
 *
 * <p>Feliratkozik a GUI frissítésre, és képes reagálni Tecton gombokra kattintásra.</p>
 */
public class ContentPanel extends JPanel implements ActionListener, IUpdateGUI {

    /** A gombatörzseket megjelenítő panel. */
    private final StemsPanel stemsPanel = new StemsPanel();

    /** A gombaszálakat megjelenítő panel. */
    private final ThreadsPanel threadsPanel = new ThreadsPanel();

    /** A spórákat megjelenítő panel. */
    private final SporesPanel sporesPanel = new SporesPanel();

    /** A rovarokat megjelenítő panel. */
    private final InsectsPanel insectPanel = new InsectsPanel();

    /** A jelenleg kiválasztott tecton példány. */
    private Tecton tecton = null;

    /** A jelenleg kiválasztott tectonhoz tartozó gomb. */
    private TectonButton tectonButton = null;

    /**
     * Konstruktor, amely inicializálja a megjelenő paneleket és beállítja az elrendezést.
     */
    public ContentPanel() {
        setPreferredSize(new Dimension(400, 200));
        setLayout(new BorderLayout());
        setBorder(createTitledBorder("Details"));
        setBackground(Color.WHITE);

        // Rácselrendezés: 2 sor, 2 oszlop
        setLayout(new GridLayout(2, 2));
        add(stemsPanel);
        add(sporesPanel);
        add(threadsPanel);
        add(insectPanel);

        Controller.subscribeGUIUpdate(this);
    }

    /**
     * Címkézett keret létrehozása a panelhez.
     *
     * @param title a cím, ami megjelenik a szegélyen
     * @return a létrehozott szegély
     */
    private Border createTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)), title
        );
    }

    /**
     * Visszaadja a spórákat megjelenítő panel referenciáját.
     */
    public BasePanel getSporesPanel() {
        return sporesPanel;
    }

    /**
     * Visszaadja a gombaszálakat megjelenítő panel referenciáját.
     */
    public BasePanel getThreadsPanel() {
        return threadsPanel;
    }

    /**
     * Visszaadja a gombatörzseket megjelenítő panel referenciáját.
     */
    public BasePanel getStemsPanel() {
        return stemsPanel;
    }

    /**
     * Visszaadja a rovarokat megjelenítő panel referenciáját.
     */
    public BasePanel getInsectPanel() {
        return insectPanel;
    }

    /**
     * A Tecton gomb megnyomására reagál. Kiemeli a kiválasztott Tectont,
     * beállítja a fejlécet, és frissíti a részletező paneleket.
     *
     * @param e az esemény, amely tartalmazza a forrást (TectonButton)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (tectonButton != null) {
            tectonButton.highlight(false);
        }

        tectonButton = (TectonButton) e.getSource();
        tectonButton.highlight(true);

        tecton = tectonButton.tecton;

        ((TitledBorder) getBorder()).setTitle("Details (" + tecton.getName() + ")");

        threadsPanel.addPanel(tecton);
        sporesPanel.addPanel(tecton);
        stemsPanel.addPanel(tecton);
        insectPanel.addPanel(tecton);

        repaint();
        validate();
    }

    /**
     * Frissíti a panelen megjelenített adatokat, ha van aktív Tecton.
     */
    @Override
    public void GUIupdate() {
        if (tecton != null) {
            threadsPanel.addPanel(tecton);
            sporesPanel.addPanel(tecton);
            stemsPanel.addPanel(tecton);
            insectPanel.addPanel(tecton);
        }
    }

    /*
    // Opcionálisan használható, ha ki akarjuk üríteni a panelt
    public void clear() {
        tecton = null;
    }
    */
}
