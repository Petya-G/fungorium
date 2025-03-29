package effect;

import core.Debug;
import insect.*;

public class ClawParalyzeEffect extends Effect {

    /**
     * Érvényesíti a hatást egy rovarra
     *
     * @param i A rovar, amire kifejti hatását
     */
    public void apply(Insect i) {
        i.setClawParalyzed(true);
        duration--;
        Debug.DBGFUNC("Rágó kábító hatás van érvényben a rovaron");
    }

    /**
     * Érvényteleníti a hatást egy rovarra
     *
     * @param i A rovar, amire érvényteleníti hatását
     */
    public void remove(Insect i) {
        i.setClawParalyzed(false);
        Debug.DBGFUNC("Rágó kábító hatás lejárt");
    }
}
