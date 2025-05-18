package view.game.buttons;

import javax.swing.*;
import java.awt.*;

/**
 * A {@code GameButton} egy absztrakt osztály, amely a játékbeli GUI elemek (ikonos gombok)
 * megjelenítésére szolgál. Alapértelmezett méretet, skálázást és megjelenési beállításokat biztosít.
 *
 * Minden leszármazott gomb reprezentálhat például spórát, rovart vagy más játékelement.
 */
public abstract class GameButton extends JButton {

    /** A gomb rögzített mérete (szélesség és magasság pixelben). */
    protected static final int size = 50;

    /** A gombhoz tartozó eredeti képikon. */
    protected ImageIcon imageIcon;

    /** A szülő panel, amelyhez a gomb tartozik. */
    protected JPanel parent;

    /**
     * Konstruktor, amely inicializálja a gombot egy képikonnal és szülő panellel.
     * Az ikon automatikusan átméretezésre kerül a szabványos méretre.
     *
     * @param imageIcon a megjelenítendő képikon
     * @param parent a szülő panel, amely tartalmazza a gombot
     */
    public GameButton(ImageIcon imageIcon, JPanel parent) {
        super(scaleImageIcon(imageIcon));
        this.imageIcon = imageIcon;
        this.parent = parent;

        setPreferredSize(new Dimension(size, size));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(true);
    }

    /**
     * Képikon átméretezése a szabványos {@code size} méretre.
     *
     * @param icon az átméretezendő képikon
     * @return az új, méretezett ImageIcon példány
     */
    protected static ImageIcon scaleImageIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
}
