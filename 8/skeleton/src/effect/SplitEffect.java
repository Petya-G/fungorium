package effect;

import core.Debug;
import insect.*;

public class SplitEffect extends Effect {

    /**
     * Konstruktor, beállítja a hatás időtartamát
     */
    public SplitEffect() {
        duration = 3;
    }

    /**
     * Érvényesíti a hatást egy rovarra
     * @param i A rovar, amire kifejti hatását
     */
    public void apply(Insect i) {
       //i.setSplitEffect(true);
       //TODO
       Debug.DBGFUNC("Osztódás hatás van érvényben a rovaron");
    }
}
