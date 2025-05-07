package model.mushroom.spore;

import controller.GameObjectVisitor;
import model.effect.SplitEffect;
import model.mushroom.Mushroomer;
import model.tecton.Tecton;

public class SplitterSpore extends Spore {

    public SplitterSpore(Mushroomer owner, Tecton location) {
        super(owner, location);
        effect = new SplitEffect();
    }

    public SplitterSpore(Mushroomer owner, Tecton location, int id) {
        super(owner, location, id);
        effect = new SplitEffect();
    }

    public SplitterSpore(SplitterSpore spore) {
        super(spore);
    }

    public void accept(GameObjectVisitor visitor) {
        visitor.visit(this);
    }
}