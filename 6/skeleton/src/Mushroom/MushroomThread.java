package mushroom;

import core.Entity;
import tecton.Tecton;

public class MushroomThread extends Entity {

    public MushroomThread(Mushroomer owner, Tecton location) {
        this.owner = owner;
        this.location = location;
    }

    @Override
    public void remove() {
        getLocation().remove(this);
        ((Mushroomer) getOwner()).remove(this);
    }

    @Override
    public void endTurn() {
    }
}
