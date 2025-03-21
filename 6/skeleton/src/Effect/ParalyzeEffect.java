package effect;

import insect.*;

public class ParalyzeEffect extends Effect {

    public ParalyzeEffect() {
        duration = 3;
    }

    public void apply(Insect i) {
        i.setParalyzed(true);
        duration--;
    }

    public void remove(Insect i) {
        i.setParalyzed(false);
    }
}
