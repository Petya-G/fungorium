package controller;

import model.core.GameObject;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.tecton.*;

import java.awt.*;

public class DrawVisitor implements GameObjectVisitor {
    static int tectonWidth = 100, tectonHeight = 100;
    protected Graphics2D g;
    protected Dimension size;

    public DrawVisitor(Graphics2D g, Dimension size) {
        this.g = g;
        this.size = size;
    }

    int tectonTopLeftX(double coordX) {
        return (int) (coordX * (size.getWidth() - tectonWidth));
    }

    int tectonTopLeftY(double coordY) {
        return (int) (coordY * (size.getHeight() - tectonHeight));
    }

    int tectonCenterX(double coordX) {
        return (int) (coordX * (size.getWidth() - tectonWidth) + tectonWidth / 2);
    }

    int tectonCenterY(double coordY) {
        return (int) (coordY * (size.getHeight() - tectonHeight) + tectonHeight / 2);
    }

    @Override
    public void visit(GameObject gameObject) {

    }

    @Override
    public void visit(LifeSupportTecton tecton) {
        drawTectonBase(tecton);
    }

    @Override
    public void visit(SingleThreadedTecton tecton) {
        drawTectonBase(tecton);
    }

    @Override
    public void visit(StemlessTecton tecton) {
        drawTectonBase(tecton);
    }

    @Override
    public void visit(Tecton tecton) {
        drawTectonBase(tecton);
    }

    @Override
    public void visit(ThreadConsumingTecton tecton) {
        drawTectonBase(tecton);
    }

    void connectTectons(Tecton t1, Tecton t2) {
        int t1x = tectonCenterX(t1.getPosX());
        int t2x = tectonCenterX(t2.getPosX());//(int)(t2.getPosX()*(size.getWidth() - tectonWidth)) + tectonWidth / 2;

        int t1y = tectonCenterY(t1.getPosY());
        int t2y = tectonCenterY(t2.getPosY());//(int)(t2.getPosY()*(size.getHeight() - tectonHeight )) + tectonHeight / 2;

        g.drawLine(t1x, t1y, t2x, t2y);
    }

    void connectTectonsWithThread(Tecton t1, Tecton t2, Color c, int offset) {
        int t1x = tectonCenterX(t1.getPosX()) + offset;
        int t2x = tectonCenterX(t2.getPosX()) + offset;

        int t1y = tectonCenterY(t1.getPosY()) + offset;
        int t2y = tectonCenterY(t2.getPosY()) + offset;
        g.setColor(c);
        g.drawLine(t1x, t1y, t2x, t2y);
        g.setColor(new Color(0, 0, 0));
    }

    void drawTectonBase(Tecton tecton) {
        for (Tecton n : tecton.getNeighbours()) {
            if (n.getId() > tecton.getId()) {
                connectTectons(n, tecton);
                for (MushroomThread thread : tecton.getThreads()) {
                    for (MushroomThread thread2 : n.getThreads()) {
                        if (thread.getOwner().equals(thread2.getOwner())) {
                            switch (((Mushroomer) thread.getOwner()).getShroomerID()) {
                                case 1:
                                    connectTectonsWithThread(n, tecton, new Color(0, 0, 255), 10);
                                    break;
                                case 2:
                                    connectTectonsWithThread(n, tecton, new Color(0, 255, 0), -10);
                                    break;
                                case 3:
                                    connectTectonsWithThread(n, tecton, new Color(255, 0, 0), 20);
                                    break;
                                case 4:
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

        g.setColor(new Color(0, 255, 255));
        g.fillOval(tectonTopLeftX(tecton.getPosX()), tectonTopLeftY(tecton.getPosY()), tectonWidth, tectonHeight);

        g.setColor(new Color(0, 0, 0));
        g.drawOval(tectonTopLeftX(tecton.getPosX()), tectonTopLeftY(tecton.getPosY()), tectonWidth, tectonHeight);
    }
}