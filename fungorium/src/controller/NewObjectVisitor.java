package controller;

import model.core.GameObject;
import model.tecton.*;
import view.game.TectonButton;

public class NewObjectVisitor implements GameObjectVisitor {

    @Override
    public void visit(GameObject gameObject) {

    }

    @Override
    public void visit(LifeSupportTecton lifeSupportTecton) {
        TectonButton tb = new TectonButton(lifeSupportTecton);
        Controller.getInstance().view.getMapPanel().tectonButtons.add(tb);
        Controller.getInstance().view.getMapPanel().add(tb);
    }

    @Override
    public void visit(SingleThreadedTecton singleThreadedTecton) {
        TectonButton tb = new TectonButton(singleThreadedTecton);
        Controller.getInstance().view.getMapPanel().tectonButtons.add(tb);
        Controller.getInstance().view.getMapPanel().add(tb);
    }

    @Override
    public void visit(StemlessTecton stemlessTecton) {
        TectonButton tb = new TectonButton(stemlessTecton);
        Controller.getInstance().view.getMapPanel().tectonButtons.add(tb);
        Controller.getInstance().view.getMapPanel().add(tb);
    }

    @Override
    public void visit(Tecton tecton) {
        TectonButton tb = new TectonButton(tecton);
        Controller.getInstance().view.getMapPanel().tectonButtons.add(tb);
        Controller.getInstance().view.getMapPanel().add(tb);
    }

    @Override
    public void visit(ThreadConsumingTecton threadConsumingTecton) {
        TectonButton tb = new TectonButton(threadConsumingTecton);
        Controller.getInstance().view.getMapPanel().tectonButtons.add(tb);
        Controller.getInstance().view.getMapPanel().add(tb);
    }
}
