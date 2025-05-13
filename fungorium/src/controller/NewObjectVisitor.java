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
        Controller.getMapPanel().addTectonButton(new TectonButton(lifeSupportTecton, ImageManager.getIcon("tecton_lifesupport"), Controller.getMapPanel()));
    }

    @Override
    public void visit(SingleThreadedTecton singleThreadedTecton) {
        Controller.getMapPanel().addTectonButton( new TectonButton(singleThreadedTecton, ImageManager.getIcon("tecton_singlethreaded"), Controller.getMapPanel()));
    }

    @Override
    public void visit(StemlessTecton stemlessTecton) {
        Controller.getMapPanel().addTectonButton( new TectonButton(stemlessTecton, ImageManager.getIcon("tecton_stemless"), Controller.getMapPanel()));
    }

    @Override
    public void visit(Tecton tecton) {
        Controller.getMapPanel().addTectonButton( new TectonButton(tecton, ImageManager.getIcon("tecton_basic"), Controller.getMapPanel()));
    }

    @Override
    public void visit(ThreadConsumingTecton threadConsumingTecton) {
        Controller.getMapPanel().addTectonButton( new TectonButton(threadConsumingTecton, ImageManager.getIcon("tecton_threadconsuming"), Controller.getMapPanel()));
    }
}
