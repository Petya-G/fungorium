package effect;

import core.Debug;
import insect.*;

public class FastEffect extends Effect {

    public FastEffect() {
        duration = 3;
    }

    public void apply(Insect i) {
        i.setSpeedModifier(2);
        duration--;
        Debug.DBGFUNC("Gyorsító hatás van érvényben a rovaron");
    }

    public void remove(Insect i) {
        i.setSpeedModifier(0);
        duration--;
        Debug.DBGFUNC("Gyorsító hatás lejárt");
    }
}
