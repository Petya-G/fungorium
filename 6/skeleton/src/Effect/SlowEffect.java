package effect;

import insect.*;

public class SlowEffect extends Effect {

    public SlowEffect() {
        duration = 3;
    }

    public void apply(Insect i) {
        i.setSpeedModifier(0.5);
        duration--;
    }

    public void remove(Insect i) {
        i.setSpeedModifier(0);
    }
}
