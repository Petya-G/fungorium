package mushroom.spore;

import mushroom.Mushroomer;
import core.Entity;
import effect.*;

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
