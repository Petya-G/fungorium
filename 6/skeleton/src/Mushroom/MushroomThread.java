package mushroom;

import core.Entity;

public class MushroomThread extends Entity{
    @Override
    public void remove() {
        getLocation().remove(this);
        ((Mushroomer) getOwner()).remove(this);
    }

    @Override
    public void endTurn() {
    }
}
