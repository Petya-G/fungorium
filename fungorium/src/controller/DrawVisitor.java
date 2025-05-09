package controller;

import model.insect.Insect;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.spore.*;
import model.tecton.*;

import javax.swing.*;

import java.awt.*;

public class DrawVisitor implements GameObjectVisitor {

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
        int t1x = (int)(t1.getPosX()*(size.getWidth() - 100)) + 50;
        int t2x = (int)(t2.getPosX()*(size.getWidth() - 100)) + 50;

        int t1y = (int)(t1.getPosY()*(size.getHeight() - 100 )) + 50;
        int t2y = (int)(t2.getPosY()*(size.getHeight() - 100 )) + 50;

        g.drawLine(t1x, t1y, t2x, t2y);
    }

    void drawTectonBase(Tecton tecton) {

        for (Tecton n : tecton.getNeighbours()) {
            if (n.getId() > tecton.getId()) {
                connectTectons(n, tecton);
            }
        }

        g.setColor(new Color(0,255,255));
        g.fillOval((int)(tecton.getPosX()*(size.getWidth() - 100)), (int)(tecton.getPosY()*(size.getHeight() - 100)), 100, 100);
        
        g.setColor(new Color(0,0,0));
        
        g.drawOval((int)(tecton.getPosX()*(size.getWidth() - 100)), (int)(tecton.getPosY()*(size.getHeight() - 100)), 100, 100);
        System.out.println(tecton.getId() + " " + tecton.getPosX());

        
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
