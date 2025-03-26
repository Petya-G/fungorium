package mushroom.spore;

import effect.SplitEffect;
import mushroom.Mushroomer;
import tecton.Tecton;

public class SplitterSpore extends Spore {
    public SplitterSpore(Mushroomer owner, Tecton location) {
        this.owner = owner;
        this.location = location;
        nutrition = 10;
        effect = new SplitEffect();
    }
}