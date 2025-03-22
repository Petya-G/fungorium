package effect;

import core.Debug;
import insect.*;

public class ClawParalyzeEffect extends Effect {

    public ClawParalyzeEffect() {
        duration = 3;
    }

    public void apply(Insect i) {
       i.setClawParalyzed(true);
       duration--;
       Debug.DBGFUNC("Rágó kábító hatás van érvényben a rovaron");
    }

    public void remove(Insect i) {
        i.setClawParalyzed(false);
        Debug.DBGFUNC("Rágó kábító hatás lejárt");
    }
}
