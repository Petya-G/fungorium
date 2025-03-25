package mushroom;

import core.Debug;
import core.Entity;
import tecton.Tecton;

public class MushroomThread extends Entity {

    /**
     * Konstruktor
     * 
     * @param owner    A gombafonalat tulajdonló gombász
     * @param location A tekton, amin a gombafonál van
     */
    public MushroomThread(Mushroomer owner, Tecton location) {
        this.owner = owner;
        this.location = location;
        Debug.DBGFUNC("Gombafonal létrehozva");
    }

    /**
     * Thread törlése a pályáról
     */
    @Override
    public void remove() {
        getLocation().remove(this);
        ((Mushroomer) getOwner()).remove(this);
        Debug.DBGFUNC("Gombafonal törölve");
    }

    /**
     * Kör vége
     */
    @Override
    public void endTurn() {
        Debug.DBGFUNC("Kör vége");
    }

    /*
     * public List<Thread> getConnected() {
     * }
     */
}
