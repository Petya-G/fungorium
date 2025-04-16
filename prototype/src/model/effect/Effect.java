package model.effect;

import model.core.Identifiable;
import model.insect.Insect;

import java.util.Objects;

/**
 * A rovarokra érvényes hatások őse, absztrakt, nem példányosítható
 */
public abstract class Effect extends Identifiable{
    int duration = 3;

    public Effect() {
        super();
    }

    public Effect(int id) {
        super(id);
    }

    /**
     * Visszaadja a hatás leteltéig hátralévő időt
     *
     * @return A hatás leteltéig hátralévő idő
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Érvényesíti a hatást egy rovarra
     *
     * @param i A rovar, amire kifejti hatását
     */
    public abstract void apply(Insect i);

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Effect effect = (Effect) o;
        return duration == effect.duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), duration);
    }
}