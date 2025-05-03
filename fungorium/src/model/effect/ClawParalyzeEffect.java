package model.effect;

import model.insect.*;

public class ClawParalyzeEffect extends Effect {

    /**
     * Érvényesíti a hatást egy rovarra
     *
     * @param i A rovar, amire kifejti hatását
     */
    public void apply(Insect i) {
        duration--;
        i.setClawParalyzed(true);
    }
}
