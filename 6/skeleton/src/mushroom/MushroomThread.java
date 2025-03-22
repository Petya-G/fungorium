package mushroom;

import core.Entity;
import tecton.Tecton;

public class MushroomThread extends Entity {

    public MushroomThread(Mushroomer owner, Tecton location) {
        this.owner = owner;
        this.location = location;
        System.out.println("Gombafonal létrehozva");
    }

    @Override
    public void remove() {
        getLocation().remove(this);
        ((Mushroomer) getOwner()).remove(this);
        System.out.println("Gombafonal törölve");
    }

    @Override
    public void endTurn() {
    }
}
