package model.mushroom.spore;

import controller.GameObjectVisitor;
import model.effect.FastEffect;
import model.mushroom.Mushroomer;
import model.tecton.Tecton;

public class SpeedingSpore extends Spore {

    public SpeedingSpore(Mushroomer owner, Tecton location) {
        super(owner, location);
        effect = new FastEffect();
    }

    public SpeedingSpore(Mushroomer owner, Tecton location, int id) {
        super(owner, location, id);
        effect = new FastEffect();
    }

    public SpeedingSpore(SpeedingSpore spore) {
        super(spore);
    }

    public void accept(GameObjectVisitor visitor) {
        visitor.visit(this);
    }
}
