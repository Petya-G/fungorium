package mushroom.spore;

import core.Entity;
import effect.*;
import mushroom.Mushroomer;

public abstract class Spore extends Entity {
    int nutrition;
    Effect effect;

    public Effect getEffect(){
        return effect;
    }

    @Override
    public void remove() {
        getLocation().remove(this);
        ((Mushroomer) getOwner()).remove(this);
    }
}
