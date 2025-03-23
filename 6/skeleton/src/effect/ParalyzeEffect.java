package effect;

import core.Debug;
import insect.*;

public class ParalyzeEffect extends Effect {

    /**
     * Konstruktor, beállítja a hatás időtartamát
     */
    public ParalyzeEffect() {
        duration = 3;
    }

    /**
     * Érvényesíti a hatást egy rovarra
     * @param i A rovar, amire kifejti hatását
     */
    public void apply(Insect i) {
        i.setParalyzed(true);
        duration--;
        Debug.DBGFUNC("Kábító hatás van érvényben a rovaron");
    }

    /**
     * Érvényteleníti a hatást egy rovarra
     * @param i A rovar, amire érvényteleníti hatását
     */
    public void remove(Insect i) {
        i.setParalyzed(false);
        Debug.DBGFUNC("Kábító hatás lejárt");
    }
}
