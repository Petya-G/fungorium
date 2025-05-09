package controller;

import model.insect.Insect;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.mushroom.spore.*;
import model.tecton.*;

import javax.swing.*;

import java.awt.*;

public class DrawVisitor implements GameObjectVisitor {

    static int tectonWidth = 100, tectonHeight = 100;

    int tectonTopLeftX(double coordX)
    {
        return (int)(coordX*(size.getWidth() - tectonWidth));
    }

    int tectonTopLeftY(double coordY)
    {
        return (int)(coordY*(size.getHeight() - tectonHeight));
    }

    int tectonCenterX(double coordX)
    {
        return (int)(coordX*(size.getWidth() - tectonWidth) + tectonWidth / 2);
    }

    int tectonCenterY(double coordY)
    {
        return (int)(coordY*(size.getHeight() - tectonHeight) + tectonHeight / 2);
    }

    protected Graphics2D g;
    public void setGraphics(Graphics2D _g) {
        g = _g;
    }

    protected Dimension size;
    public void setSize(Dimension d) {
        size = d;
    }

    @Override
    public void visit(Insect insect) {
        //TODO
    }

    @Override
    public void visit(LifeSupportTecton tecton) {
        //TODO
        drawTectonBase(tecton);
    }

    @Override
    public void visit(SingleThreadedTecton tecton) {
        //TODO
        drawTectonBase(tecton);
    }

    @Override
    public void visit(StemlessTecton tecton) {

        //TODO
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
        g.setColor(new Color(0,0,0));
        //System.out.println("Anya, anya, meghívtak");
    }

    void drawTectonBase(Tecton tecton) {

        for (Tecton n : tecton.getNeighbours()) {
            if (n.getId() > tecton.getId()) {
                connectTectons(n, tecton);
                for (MushroomThread thread : tecton.getThreads()) {
                    for (MushroomThread thread2 : n.getThreads()) {
                        if(thread.getOwner().equals(thread2.getOwner()))
                        {
                            //System.out.print(thread.getLocation().getId() + "\t");
                            switch (((Mushroomer)thread.getOwner()).getShroomerID()) {
                                case 1: connectTectonsWithThread(n, tecton, new Color(0,0,255), 10); break;
                                case 2: connectTectonsWithThread(n, tecton, new Color(0,255,0), -10); break;
                                case 3: connectTectonsWithThread(n, tecton, new Color(255,0,0), 20); break;
                                case 4: connectTectonsWithThread(n, tecton, new Color(255,255,0), -20); break;
                            
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println();
        g.setColor(new Color(0,255,255));
        g.fillOval(tectonTopLeftX(tecton.getPosX()), tectonTopLeftY(tecton.getPosY()), tectonWidth, tectonHeight);
        
        g.setColor(new Color(0,0,0));
        
        g.drawOval(tectonTopLeftX(tecton.getPosX()), tectonTopLeftY(tecton.getPosY()), tectonWidth, tectonHeight);
        //System.out.println(tecton.getId() + " " + tecton.getPosX());

        
    }

    @Override
    public void visit(Tecton tecton) {

        //g.drawRect(0, 0, 20, 20);
        //TODO
        
        drawTectonBase(tecton);
    }

    @Override
    public void visit(ThreadConsumingTecton tecton) {

        //TODO
        drawTectonBase(tecton);
    }

    @Override
    public void visit(MushroomThread mushroomThread) {

        //TODO
    }

    @Override
    public void visit(MushroomStem mushroomStem) {

        //TODO
    }

    @Override
    public void visit(ClawParalyzingSpore clawParalyzingSpore) {

        //TODO
    }

    @Override
    public void visit(ParalyzingSpore paralyzingSpore) {

        //TODO
    }

    @Override
    public void visit(SlowingSpore slowingSpore) {

        //TODO
    }

    @Override
    public void visit(SpeedingSpore speedingSpore) {

        //TODO
    }

    @Override
    public void visit(SplitterSpore splitterSpore) {

        //TODO
    }
}
