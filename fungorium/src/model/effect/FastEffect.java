package model.effect;

import controller.GameObjectVisitor;
import model.insect.*;

public class FastEffect extends Effect {
    /**
     * Érvényesíti a hatást egy rovarra
     *
     * @param i A rovar, amire kifejti hatását
     */
    public void apply(Insect i) {
        duration--;
        i.setSpeedModifier(2);
    }

    @Override
    public void accept(GameObjectVisitor visitor) {

    }
}
