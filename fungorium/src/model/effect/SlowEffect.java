package model.effect;

import controller.GameObjectVisitor;
import model.insect.*;

public class SlowEffect extends Effect {
    /**
     * Érvényesíti a hatást egy rovarra
     *
     * @param i A rovar, amire kifejti hatását
     */
    public void apply(Insect i) {
        duration--;
        i.setSpeedModifier(0.5);
    }

    @Override
    public void accept(GameObjectVisitor visitor) {

    }
}
