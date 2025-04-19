package model.mushroom.spore;

import model.effect.SplitEffect;
import model.mushroom.Mushroomer;
import model.tecton.Tecton;

public class SplitterSpore extends Spore {

    public SplitterSpore(Mushroomer owner, Tecton location) {
        super(owner, location);
        effect = new SplitEffect();
    }
}