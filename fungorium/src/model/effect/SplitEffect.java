package model.effect;

import controller.GameObjectVisitor;
import model.insect.Insect;

public class SplitEffect extends Effect {
    public SplitEffect() {
        duration = 1;
    }

    @Override
    public void accept(GameObjectVisitor visitor) {

    }

    @Override
    public void apply(Insect i) {
        duration--;
        i.split();
    }

}