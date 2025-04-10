package effect;

import core.Debug;
import insect.*;

public class ParalyzeEffect extends Effect {
    /**
     * Érvényesíti a hatást egy rovarra
     *
     * @param i A rovar, amire kifejti hatását
     */
    public void apply(Insect i) {
        duration--;
        i.setParalyzed(true);
        Debug.DBGFUNC("Kábító hatás van érvényben a rovaron");
    }
}
