package effect;

import insect.Insect;

public class SplitEffect extends Effect {
    public SplitEffect() {
        duration = 1;
    }

    @Override
    public void apply(Insect i) {
        i.Split();
    }

    @Override
    public void remove(Insect i) {
    }
}