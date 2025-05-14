package model.tecton;

import controller.visitor.GameObjectVisitor;

/**
 * Olyan tekton fajta, amin nem pusztulnak el a gombafonalak, ha nincsenek a gombafonalak összekötve gombatesttel.
 */
public class LifeSupportTecton extends Tecton {
    @Override
    public void removeUnconnectedThreads() {

    }

    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }
}
