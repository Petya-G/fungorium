package view.game.contentpanel;

import model.tecton.Tecton;
import view.IUpdateGUI;
import view.game.buttons.GameButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * A {@code BasePanel} egy absztrakt alappanel, amely a játéktartalom (pl. szálak, spórák, rovarok)
 * GUI elemeinek megjelenítésére szolgál a {@code ContentPanel} részeként.
 *
 * <p>Alapértelmezett balra zárt, szellős elrendezéssel dolgozik, és címkézett szegélyt használ.</p>
 *
 * <p>A konkrét származtatott panelek (pl. {@code ThreadPanel}, {@code SporesPanel}) egy-egy Tecton alapján
 * frissítik a saját tartalmukat.</p>
 */
public abstract class BasePanel extends JPanel {

    /**
     * Konstruktor, amely létrehozza a panelt egy címkézett kerettel.
     *
     * @param title a panel szegélyének címe
     */
    public BasePanel(String title) {
        super(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.setBorder(createTitledBorder(title));
    }

    /**
     * Létrehozza a szegélyt a megadott címmel és stílussal.
     *
     * @param title a szegély címe
     * @return a létrehozott {@code Border}
     */
    protected Border createTitledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        return border;
    }

    /**
     * Absztrakt metódus: a panel frissítése egy adott Tecton alapján.
     *
     * @param tecton a megjelenítendő objektum
     */
    public abstract void addPanel(Tecton tecton);

    /**
     * Egy komponens (gomb) hozzáadása a panelhez, és a GUI frissítése.
     *
     * @param button a hozzáadandó {@code GameButton}
     */
    public void addComponent(GameButton button) {
        add(button);
        revalidate();
        repaint();
    }
}
