package model.mushroom.spore;

import model.mushroom.Mushroomer;
import model.tecton.Tecton;
import model.core.Entity;
import model.core.Player;
import model.effect.*;

public abstract class Spore extends Entity {

    protected Spore(Tecton location, Player owner, int nutrition, Effect effect) {
        super(location, owner);
        this.nutrition = nutrition;
        this.effect = effect;
    }

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
    }

    @Override
    public void setLocation(Tecton location) {
        getLocation().remove(this);
        location.add(this);
        super.setLocation(location);
    }
}
