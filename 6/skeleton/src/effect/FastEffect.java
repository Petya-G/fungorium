package effect;

import insect.*;

public class FastEffect extends Effect {

    public FastEffect() {
        duration = 3;
    }

    public void apply(Insect i) {
        i.setSpeedModifier(2);
        duration--;
    }

    public void remove(Insect i) {
        i.setSpeedModifier(0);
        duration--;
    }
}
