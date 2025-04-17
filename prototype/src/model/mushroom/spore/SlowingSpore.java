package model.mushroom.spore;

import model.effect.SlowEffect;
import model.mushroom.Mushroomer;
import model.tecton.Tecton;

public class SlowingSpore extends Spore {

    public SlowingSpore(Mushroomer owner, Tecton location) {
        super(owner, location);
        effect = new SlowEffect();
        nutrition = 10;
    }
}
