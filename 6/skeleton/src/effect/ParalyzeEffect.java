package effect;

import core.Debug;
import insect.*;

public class ParalyzeEffect extends Effect {

    public ParalyzeEffect() {
        duration = 3;
    }

    public void apply(Insect i) {
        i.setParalyzed(true);
        duration--;
        Debug.DBGFUNC("Kábító hatás van érvényben a rovaron");
    }

    public void remove(Insect i) {
        i.setParalyzed(false);
        Debug.DBGFUNC("Kábító hatás lejárt");
    }
}
