package effect;

import insect.*;

/**
 * A rovarokra érvényes hatások őse, absztrakt, nem példányosítható
 */
public abstract class Effect {
    int duration;

    /**
     * Visszaadja a hatás leteltéig hátralévő időt
     * @return A hatás leteltéig hátralévő idő
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Érvényesíti a hatást egy rovarra
     * @param i A rovar, amire kifejti hatását
     */
    public abstract void apply(Insect i);

    /**
     * Érvényteleníti a hatást egy rovarra
     * @param i A rovar, amire érvényteleníti hatását
     */
    public abstract void remove(Insect i);
}