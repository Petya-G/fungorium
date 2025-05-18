package view.game;

import javax.swing.*;
import java.awt.*;

/**
 * Az {@code ImagePanel} egy egyszerű panel, amely képek megjelenítésére szolgál.
 *
 * <p>Lehetővé teszi a kép tetszőleges (x, y) pozícióba történő elhelyezését, és a {@code paintComponent} metódus
 * felülírásával rajzolja ki azt a panelre.</p>
 */
public class ImagePanel extends JPanel {

    /** A megjelenítendő kép. */
    private Image img;

    /** A kép bal felső sarkának x koordinátája. */
    private int x;

    /** A kép bal felső sarkának y koordinátája. */
    private int y;

    /**
     * Létrehozza az {@code ImagePanel} példányt és betölti a képet a megadott útvonalról.
     *
     * @param imagePath a kép fájl elérési útvonala
     */
    public ImagePanel(String imagePath) {
        img = new ImageIcon(imagePath).getImage();
        setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
    }

    /**
     * Beállítja a kép megjelenítési pozícióját.
     *
     * @param x vízszintes pozíció (bal szél)
     * @param y függőleges pozíció (felső szél)
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Visszaadja a kép aktuális x pozícióját.
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Visszaadja a kép aktuális y pozícióját.
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * A panel újrarajzolása, amely tartalmazza a képet az aktuális pozícióban.
     *
     * @param g a grafikus kontextus, amelyre rajzolunk
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, x, y, this);
    }
}
