package model.tecton;

import controller.GameObjectVisitor;

/**
 * Olyan tekton fajta, amin minden kör végén eltűnnek a gombafonalak.
 */
public class ThreadConsumingTecton extends Tecton {
    /**
     * Minden kör végén meghívódik
     */
    @Override
    public void endRound() {
        threads.clear();
    }

    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }
}
