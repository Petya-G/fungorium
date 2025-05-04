package model.effect;

import model.insect.Insect;

public class SplitEffect extends Effect {
    public SplitEffect() {
        duration = 1;
    }

    @Override
    public void apply(Insect i) {
        duration--;
        i.split();
    }

}