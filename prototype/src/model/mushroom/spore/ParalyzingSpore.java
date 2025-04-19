package model.mushroom.spore;

import model.core.Player;
import model.effect.ParalyzeEffect;
import model.tecton.Tecton;

public class ParalyzingSpore extends Spore {

    public ParalyzingSpore(Player owner, Tecton location) {
        super(owner, location);
        effect = new ParalyzeEffect();
    }
}