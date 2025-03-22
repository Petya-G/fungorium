package effect;

import core.Debug;
import insect.*;

public class SlowEffect extends Effect {

    public SlowEffect() {
        duration = 3;
    }

    public void apply(Insect i) {
        i.setSpeedModifier(0.5);
        duration--;
        Debug.DBGFUNC("Lassító hatás van érvényben a rovaron");
    }

    public void remove(Insect i) {
        i.setSpeedModifier(0);
        Debug.DBGFUNC("Lassító hatás lejárt");
    }
}
