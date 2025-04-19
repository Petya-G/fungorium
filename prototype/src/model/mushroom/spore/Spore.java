package model.mushroom.spore;

import java.util.Objects;
import model.core.Entity;
import model.core.Player;
import model.effect.Effect;
import model.mushroom.Mushroomer;
import model.tecton.Tecton;

public abstract class Spore extends Entity {
    protected final int NUTRITION=10; // A tápérték, ennyi pontot kap a rovar, mikor megeszi a spórát
    //TODO nutrition így jó?
    protected Effect effect; // A spóra rovarra gyakorolt hatása

    /**
     * Konstruktor
     *
     * @param owner Játékos
     * @param location Tekton, amin a spóra van
     */
    protected Spore(Player owner, Tecton location) {
        super(owner, location);
    }

    /**
     * Spóra hatásának lekérése
     *
     * @return A spóra hatása
     */
    public Effect getEffect() {
        return effect;
    }

    /**
     * Spóra tápértékének lekérése
     *
     * @return A spóra tápértéke
     */
    public int getNutrition() {
        return NUTRITION;
    }

    /**
     * Spóra törlése a játékból
     */
    @Override
    public void remove() {
        getLocation().remove(this);
        ((Mushroomer) getOwner()).remove(this);
    }

    /**
     * Spóra helyének beállítása
     *
     * @param location A tekton, ahol a spóra van
     */
    @Override
    public void setLocation(Tecton location) {
        getLocation().remove(this);
        location.add(this);
        super.setLocation(location);
    }

    /**
     *Összehasonlít 2 objektumot
     * @param o    Objektum, amivel összehasonlítjuk
     *@return  Igaz, ha megegyezik a 2 objektum, egyébként hamis
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Spore spore = (Spore) o;
        return Objects.equals(effect, spore.effect);
    }

    /**
     * Visszaadja az objektum hash-kódját, amely a jelenlegi példány mezői alapján kerül kiszámításra.
     * @return Az objektum hash-kódja.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), NUTRITION, effect);
    }
}