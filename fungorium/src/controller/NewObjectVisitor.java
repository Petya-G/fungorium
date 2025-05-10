package controller;

import model.core.GameObject;
import model.tecton.*;
import view.ImageManager;
import view.game.TectonButton;

public class NewObjectVisitor implements GameObjectVisitor {

    @Override
    public void visit(GameObject gameObject) {

    }

    @Override
    public void visit(LifeSupportTecton lifeSupportTecton) {
        Controller.getInstance().mapPanel.addTectonButton(new TectonButton(lifeSupportTecton, ImageManager.getIcon("tecton_lifesupport")));
    }

    @Override
    public void visit(SingleThreadedTecton singleThreadedTecton) {
        Controller.getInstance().mapPanel.addTectonButton( new TectonButton(singleThreadedTecton, ImageManager.getIcon("tecton_singlethreaded")));
    }

    @Override
    public void visit(StemlessTecton stemlessTecton) {
        Controller.getInstance().mapPanel.addTectonButton( new TectonButton(stemlessTecton, ImageManager.getIcon("tecton_stemless")));
    }

    @Override
    public void visit(Tecton tecton) {
        Controller.getInstance().mapPanel.addTectonButton( new TectonButton(tecton, ImageManager.getIcon("tecton_basic")));
    }

    @Override
    public void visit(ThreadConsumingTecton threadConsumingTecton) {
        Controller.getInstance().mapPanel.addTectonButton( new TectonButton(threadConsumingTecton, ImageManager.getIcon("tecton_threadconsuming")));
    }
}
