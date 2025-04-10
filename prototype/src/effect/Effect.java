package effect;

import core.Identifiable;
import insect.*;

/**
 * A rovarokra érvényes hatások őse, absztrakt, nem példányosítható
 */
public abstract class Effect extends Identifiable{
    int duration;

    protected Effect() {
        this.duration = 3;
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
}