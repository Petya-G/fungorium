package model.effect;

import model.insect.Insect;

public class ParalyzeEffect extends Effect {
    /**
     * Érvényesíti a hatást egy rovarra
     *
     * @param i A rovar, amire kifejti hatását
     */
    public void apply(Insect i) {
        duration--;
        i.setParalyzed(true);
    }
}
