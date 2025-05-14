package model.mushroom.spore;

import controller.visitor.GameObjectVisitor;
import model.effect.SlowEffect;
import model.mushroom.Mushroomer;
import model.tecton.Tecton;

public class SlowingSpore extends Spore {

    public SlowingSpore(Mushroomer owner, Tecton location) {
        super(owner, location);
        effect = new SlowEffect();
    }

    public SlowingSpore(Mushroomer owner, Tecton location, int id) {
        super(owner, location, id);
        effect = new SlowEffect();
    }

    public SlowingSpore(SlowingSpore spore) {
        super(spore);
    }

    public void accept(GameObjectVisitor visitor) {
        visitor.visit(this);
    }
}