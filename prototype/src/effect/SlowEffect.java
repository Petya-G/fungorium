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
        i.setSpeedModifier(0.5);
        duration--;
        Debug.DBGFUNC("Lassító hatás van érvényben a rovaron");
    }

    /**
     * Érvényteleníti a hatást egy rovarra
     *
     * @param i A rovar, amire érvényteleníti hatását
     */
    public void remove(Insect i) {
        i.setSpeedModifier(0);
        Debug.DBGFUNC("Lassító hatás lejárt");
    }
}
