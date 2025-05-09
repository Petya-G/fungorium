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
    public void visit(LifeSupportTecton lifeSupportTecton) {
        //TODO
    }

    @Override
    public void visit(SingleThreadedTecton singleThreadedTecton) {
        //TODO

    }

    @Override
    public void visit(StemlessTecton stemlessTecton) {

        //TODO
    }

    @Override
    public void visit(Tecton tecton) {

        g.drawRect(0, 0, 20, 20);
        //TODO
        
        g.drawRect((int)(tecton.getPosX()*size.getWidth()), (int)(tecton.getPosY()*size.getHeight()), 10, 10);
        System.out.println(tecton.getId() + " " + tecton.getPosX());
    }

    @Override
    public void visit(ThreadConsumingTecton threadConsumingTecton) {

        //TODO
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
