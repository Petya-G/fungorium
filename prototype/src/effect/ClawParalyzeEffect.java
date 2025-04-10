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
        duration--;
        i.setClawParalyzed(true);
        Debug.DBGFUNC("Rágó kábító hatás van érvényben a rovaron");
    }
}
