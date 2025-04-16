package model.mushroom.spore;

import model.core.Entity;
import model.core.Player;
import model.effect.Effect;
import model.mushroom.Mushroomer;
import model.tecton.Tecton;

import java.util.Objects;

public abstract class Spore extends Entity {
    int nutrition; // A tápérték, ennyi pontot kap a rovar, mikor megeszi a spórát
    Effect effect; // A spóra rovarra gyakorolt hatása

    protected Spore(Tecton location, Player owner, int nutrition, Effect effect) {
        super(location, owner);
        this.nutrition = nutrition;
        this.effect = effect;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Spore spore = (Spore) o;
        return nutrition == spore.nutrition && Objects.equals(effect, spore.effect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nutrition, effect);
    }
}
