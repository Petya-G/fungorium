package effect;

import insect.*;

public class ClawParalyzeEffect extends Effect {

    public ClawParalyzeEffect() {
        duration = 3;
    }

    public void apply(Insect i) {
       i.setClawParalyzed(true);
       duration--;
    }

    public void remove(Insect i) {
        i.setClawParalyzed(false);
    }
}
