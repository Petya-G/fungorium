package mushroom.spore;

import mushroom.Mushroomer;
import tecton.Tecton;
import core.Debug;
import core.Entity;
import effect.*;

public abstract class Spore extends Entity {
    int nutrition; // A tápérték, ennyi pontot kap a rovar, mikor megeszi a spórát
    Effect effect; // A spóra rovarra gyakorolt hatása

    /**
     * Spóra hatásának lekérése
     *
     * @return A spóra hatása
     */
    public Effect getEffect() {
        return effect;
    }

    public int getNutrition() {
        return nutrition;
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

    @Override
    public void setLocation(Tecton location) {
        this.location.remove(this);
        location.add(this);
        super.setLocation(location);
    }
}
