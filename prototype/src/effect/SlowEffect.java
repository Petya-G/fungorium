package effect;

import core.Debug;
import insect.*;

public class SlowEffect extends Effect {
    /**
     * Érvényesíti a hatást egy rovarra
     *
     * @param i A rovar, amire kifejti hatását
     */
    public void apply(Insect i) {
        duration--;
        i.setSpeedModifier(0.5);
        Debug.DBGFUNC("Lassító hatás van érvényben a rovaron");
    }
}
