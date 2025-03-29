package mushroom.spore;

import effect.SplitEffect;
import mushroom.Mushroomer;
import tecton.Tecton;

public class SplitterSpore extends Spore {
    public SplitterSpore(Mushroomer owner, Tecton location) {
        super(location, owner, 10, new SplitEffect());
    }
}