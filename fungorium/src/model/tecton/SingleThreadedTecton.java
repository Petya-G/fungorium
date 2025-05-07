package model.tecton;

import controller.GameObjectVisitor;
import model.mushroom.MushroomThread;

/**
 * Olyan tekton fajta, amin csak egy gombafonal lehet.
 */
public class SingleThreadedTecton extends Tecton {

    /**
     * A SingleThreadedTecton-ra rárak egy gomba fonalat, az ilyen tektonon
     * legfeljebb 1db gombafonál lehet
     *
     * @param th Az elhelyezendő gombafonál
     * @return Az add sikeressége
     */
    @Override
    public boolean add(MushroomThread th) {
        if (threads.isEmpty()) {
            threads.add(th);
            return true;
        }
        return false;
    }

    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }
}
