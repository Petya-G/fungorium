package controller;

import model.core.GameObject;
import model.tecton.*;
import view.ImageManager;
import view.game.buttons.TectonButton;

public class NewObjectVisitor implements GameObjectVisitor {

    @Override
    public void visit(GameObject gameObject) {

    }

    @Override
    public void visit(LifeSupportTecton lifeSupportTecton) {
        Controller.getInstance().mapPanel.addTectonButton(new TectonButton(lifeSupportTecton, ImageManager.getIcon("tecton_lifesupport"), Controller.getInstance().mapPanel));
    }

    @Override
    public void visit(SingleThreadedTecton singleThreadedTecton) {
        Controller.getInstance().mapPanel.addTectonButton( new TectonButton(singleThreadedTecton, ImageManager.getIcon("tecton_singlethreaded"), Controller.getInstance().mapPanel));
    }

    @Override
    public void visit(StemlessTecton stemlessTecton) {
        Controller.getInstance().mapPanel.addTectonButton( new TectonButton(stemlessTecton, ImageManager.getIcon("tecton_stemless"), Controller.getInstance().mapPanel));
    }

    @Override
    public void visit(Tecton tecton) {
        Controller.getInstance().mapPanel.addTectonButton( new TectonButton(tecton, ImageManager.getIcon("tecton_basic"), Controller.getInstance().mapPanel));
    }

    @Override
    public void visit(ThreadConsumingTecton threadConsumingTecton) {
        Controller.getInstance().mapPanel.addTectonButton( new TectonButton(threadConsumingTecton, ImageManager.getIcon("tecton_threadconsuming"), Controller.getInstance().mapPanel));
    }
}
