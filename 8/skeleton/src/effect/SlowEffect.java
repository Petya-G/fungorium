package effect;

import core.Debug;
import insect.*;

public class SlowEffect extends Effect {

    /**
     * Konstruktor, beállítja a hatás időtartamát
     */
    public SlowEffect() {
        duration = 3;
    }

    /**
     * Érvényesíti a hatást egy rovarra
     * @param i A rovar, amire kifejti hatását
     */
    public void apply(Insect i) {
        i.setSpeedModifier(0.5);
        Debug.DBGFUNC("Lassító hatás van érvényben a rovaron");
    }
}
