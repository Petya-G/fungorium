package controller.visitor;

import java.awt.*;
import model.core.GameObject;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.tecton.*;

/**
 * A látogató (Visitor) minta alapján megvalósított osztály, amely
 * a játéktér objektumainak kirajzolásáért felelős.
 */
public class DrawVisitor implements GameObjectVisitor {

    /** A tecton objektumok alapértelmezett szélessége. */
    static int tectonWidth = 100;

    /** A tecton objektumok alapértelmezett magassága. */
    static int tectonHeight = 100;

    /** A grafikus kontextus, amelyre rajzolás történik. */
    protected Graphics2D g;

    /** A kirajzolt terület mérete. */
    protected Dimension size;

    /**
     * A rajzoláshoz szükséges paraméterek beállítása.
     *
     * @param g a grafikus kontextus
     * @param size a megjelenítési méret
     */
    public void setParameters(Graphics2D g, Dimension size) {
        this.g = g;
        this.size = size;
    }

    /**
     * A tecton objektum X koordinátáját középre igazítja a méret alapján.
     *
     * @param coordX a tecton relatív X koordinátája (0.0–1.0)
     * @return a képernyőn kiszámított X koordináta
     */
    int tectonCenterX(double coordX) {
        return (int) (coordX * (size.getWidth() - tectonWidth) + (double) tectonWidth / 2);
    }

    /**
     * A tecton objektum Y koordinátáját középre igazítja a méret alapján.
     *
     * @param coordY a tecton relatív Y koordinátája (0.0–1.0)
     * @return a képernyőn kiszámított Y koordináta
     */
    int tectonCenterY(double coordY) {
        return (int) (coordY * (size.getHeight() - tectonHeight) + (double) tectonHeight / 2);
    }

    /**
     * Alapértelmezett látogatási művelet – nem csinál semmit.
     *
     * @param gameObject a meglátogatott objektum
     */
    @Override
    public void visit(GameObject gameObject) {}

    /**
     * Egy LifeSupportTecton objektum kirajzolása.
     *
     * @param tecton a meglátogatott tecton
     */
    @Override
    public void visit(LifeSupportTecton tecton) {
        drawTectonBase(tecton);
    }

    /**
     * Egy SingleThreadedTecton objektum kirajzolása.
     *
     * @param tecton a meglátogatott tecton
     */
    @Override
    public void visit(SingleThreadedTecton tecton) {
        drawTectonBase(tecton);
    }

    /**
     * Egy StemlessTecton objektum kirajzolása.
     *
     * @param tecton a meglátogatott tecton
     */
    @Override
    public void visit(StemlessTecton tecton) {
        drawTectonBase(tecton);
    }

    /**
     * Egy általános Tecton objektum kirajzolása.
     *
     * @param tecton a meglátogatott tecton
     */
    @Override
    public void visit(Tecton tecton) {
        drawTectonBase(tecton);
    }

    /**
     * Egy ThreadConsumingTecton objektum kirajzolása.
     *
     * @param tecton a meglátogatott tecton
     */
    @Override
    public void visit(ThreadConsumingTecton tecton) {
        drawTectonBase(tecton);
    }

    /**
     * Két tecton közötti kapcsolat kirajzolása egy egyszerű vonallal.
     *
     * @param t1 az egyik tecton
     * @param t2 a másik tecton
     */
    void connectTectons(Tecton t1, Tecton t2) {
        int t1x = tectonCenterX(t1.getPosX());
        int t2x = tectonCenterX(t2.getPosX());

        int t1y = tectonCenterY(t1.getPosY());
        int t2y = tectonCenterY(t2.getPosY());

        g.drawLine(t1x, t1y, t2x, t2y);
    }

    /**
     * Két tecton közötti kapcsolat kirajzolása színes vonallal, amely egy threadet jelképez.
     *
     * @param t1 az egyik tecton
     * @param t2 a másik tecton
     * @param c a vonal színe
     * @param offset az eltolás a vonal pozíciójához
     */
    void connectTectonsWithThread(Tecton t1, Tecton t2, Color c, int offset) {
        int t1x = tectonCenterX(t1.getPosX()) + offset;
        int t2x = tectonCenterX(t2.getPosX()) + offset;

        int t1y = tectonCenterY(t1.getPosY()) + offset;
        int t2y = tectonCenterY(t2.getPosY()) + offset;
        g.setColor(c);
        g.drawLine(t1x, t1y, t2x, t2y);
        g.setColor(new Color(0, 0, 0));
    }

    /**
     * Egy tecton kirajzolása a szomszédaival és thread kapcsolataival együtt.
     *
     * @param tecton a kirajzolandó tecton
     */
    void drawTectonBase(Tecton tecton) {
        for (Tecton n : tecton.getNeighbours()) {
            if (n.getId() > tecton.getId()) {
                connectTectons(n, tecton);
                for (MushroomThread thread : tecton.getThreads()) {
                    for (MushroomThread thread2 : n.getThreads()) {
                        if (thread.getOwner().equals(thread2.getOwner())) {
                            switch (((Mushroomer) thread.getOwner()).getShroomerID()) {
                                case 0:
                                    connectTectonsWithThread(n, tecton, new Color(0, 0, 255), 10);
                                    break;
                                case 1:
                                    connectTectonsWithThread(n, tecton, new Color(0, 255, 0), -10);
                                    break;
                                case 2:
                                    connectTectonsWithThread(n, tecton, new Color(255, 0, 0), 20);
                                    break;
                                case 3:
                                    connectTectonsWithThread(n, tecton, new Color(255, 255, 0), -20);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }
}
