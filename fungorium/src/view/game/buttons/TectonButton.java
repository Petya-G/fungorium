package view.game.buttons;

import controller.Action;
import controller.Controller;
import model.insect.Insect;
import model.mushroom.MushroomStem;
import model.tecton.Tecton;
import view.game.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A {@code TectonButton} osztály a játék térképén megjelenített tecton objektumokat reprezentáló gomb.
 *
 * Lehetővé teszi az interakciót különböző akciókkal (pl. mozgás, szál növesztés, spóra dobás),
 * valamint lehetőség van a tecton pozíciójának drag-elésére (húzás).
 */
public class TectonButton extends GameButton {

    /** A gombhoz tartozó tecton objektum. */
    public Tecton tecton;

    /** Jelzi, hogy a gomb ki van-e emelve (piros szegéllyel). */
    private boolean hasHighlight = false;

    /**
     * Új {@code TectonButton} példány létrehozása.
     *
     * @param tecton a megjelenítendő tecton
     * @param imageIcon a gomb ikonja
     * @param parent a szülő térkép panel
     */
    public TectonButton(Tecton tecton, ImageIcon imageIcon, MapPanel parent) {
        super(imageIcon, parent);
        this.tecton = tecton;

        // A tecton pozíciójának beállítása a térképen
        setBounds(CenterX((int) tecton.getPosX()), CenterY((int) tecton.getPosY()), size, size);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point initialMousePoint;

            @Override
            public void mousePressed(MouseEvent e) {
                initialMousePoint = e.getPoint();

                // Bal egérgomb: különböző játékakciók kezelése
                if (SwingUtilities.isLeftMouseButton(e)) {
                    switch (Controller.getAction()) {
                        case MOVE:
                            if (Controller.getGame().move((Insect) Controller.getSelected(), tecton)) {
                                Controller.log("moved successfully");
                            } else {
                                Controller.log("unable to move");
                            }
                            break;

                        case THROW_SPORE:
                            if (Controller.getGame().throwSpore((MushroomStem) Controller.getSelected(), tecton)) {
                                Controller.log("spore successfully thrown");
                            } else {
                                Controller.log("unable to throw spore");
                            }
                            break;

                        case GROW_THREAD:
                            if (Controller.getGame().growThread(tecton)) {
                                Controller.log("thread grown successfully");
                            } else {
                                Controller.log("unable to grow thread");
                            }
                            break;

                        default:
                            break;
                    }
                    Controller.setAction(Action.NONE);
                    Controller.refreshView();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // Drag művelet: tecton pozíció mozgatása
                if (initialMousePoint != null) {
                    int deltaX = e.getX() - initialMousePoint.x;
                    int deltaY = e.getY() - initialMousePoint.y;

                    Point buttonLocation = getLocation();
                    int newX = buttonLocation.x + deltaX;
                    int newY = buttonLocation.y + deltaY;

                    int parentWidth = parent.getWidth();
                    int parentHeight = parent.getHeight();

                    // Pozíció korlátozása a panel méreteire
                    newX = Math.max(0, Math.min(newX, parentWidth - size));
                    newY = Math.max(0, Math.min(newY, parentHeight - size));

                    // Tecton új logikai pozíciójának kiszámítása
                    tecton.setPosX(invCenterX(newX + size / 2));
                    tecton.setPosY(invCenterY(newY + size / 2));

                    setLocation(newX, newY);
                    parent.repaint();
                }
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    /**
     * Beállítja, hogy a tecton ki legyen-e emelve.
     *
     * @param b true, ha kiemelt; false, ha nem
     */
    public void highlight(boolean b) {
        hasHighlight = b;
        revalidate();
        repaint();
    }

    /**
     * Kirajzolja a kiemelést (piros szegély), ha aktív.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (hasHighlight) {
            g.setColor(new Color(255, 0, 0));
            g2d.setStroke(new BasicStroke(10));
            g.drawRect(0, 0, size, size);
        }
    }

    /**
     * A tecton logikai pozíciója alapján újrapozicionálja a gombot.
     */
    public void refreshState() {
        setLocation(CenterX(tecton.getPosX()) - size / 2, CenterY(tecton.getPosY()) - size / 2);
    }

    /**
     * A képernyő X koordinátáját visszafordítja logikai koordinátává.
     */
    protected double invCenterX(int x) {
        return (x - size) / (parent.getSize().getWidth() - 2 * size);
    }

    /**
     * A képernyő Y koordinátáját visszafordítja logikai koordinátává.
     */
    protected double invCenterY(int y) {
        return (y - size) / (parent.getSize().getHeight() - 2 * size);
    }

    /**
     * Logikai X koordinátát képernyő koordinátává konvertál.
     */
    protected int CenterX(double coordX) {
        return (int) (coordX * (parent.getSize().getWidth() - 2 * size) + size);
    }

    /**
     * Logikai Y koordinátát képernyő koordinátává konvertál.
     */
    protected int CenterY(double coordY) {
        return (int) (coordY * (parent.getSize().getHeight() - 2 * size) + size);
    }
}
