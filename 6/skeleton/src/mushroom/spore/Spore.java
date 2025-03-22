package mushroom.spore;

import mushroom.Mushroomer;
import core.Debug;
import core.Entity;
import effect.*;

public abstract class Spore extends Entity {
    int nutrition;
    Effect effect;

    /**
     * Spóra hatásának lekérése
     */
    public Effect getEffect(){
        return effect;
    }

    /**
     * Spóra törlése a játékból
     */
    @Override
    public void remove() {
        getLocation().remove(this);
        ((Mushroomer) getOwner()).remove(this);
        Debug.DBGFUNC("Spóra törlése");
    }
}
