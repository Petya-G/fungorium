package model.mushroom.spore;

import model.effect.FastEffect;
import model.mushroom.Mushroomer;
import model.tecton.Tecton;

public class SpeedingSpore extends Spore {

    public SpeedingSpore(Mushroomer owner, Tecton location) {
        super(owner, location);
        effect = new FastEffect();
        nutrition = 10;
    }
}
