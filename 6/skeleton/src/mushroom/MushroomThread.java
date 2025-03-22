package mushroom;

import core.Debug;
import core.Entity;
import tecton.Tecton;

public class MushroomThread extends Entity {

    public MushroomThread(Mushroomer owner, Tecton location) {
        this.owner = owner;
        this.location = location;
        Debug.DBGFUNC("Gombafonal létrehozva");
    }

    @Override
    public void remove() {
        getLocation().remove(this);
        ((Mushroomer) getOwner()).remove(this);
        Debug.DBGFUNC("Gombafonal törölve");
    }

    @Override
    public void endTurn() {
    }
}
