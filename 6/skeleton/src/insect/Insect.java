package insect;

import core.Entity;
import effect.Effect;
import mushroom.spore.Spore;
import tecton.Tecton;
import mushroom.MushroomThread;
import java.util.List;

public class Insect extends Entity implements IInsect {
    protected List<Effect> effects;
    protected boolean paralyzed;
    protected boolean clawParalyzed;
    protected int baseSpeed;
    protected int speedModifier;

    public void apply(Effect e) {
        // Implementation for applying an effect
    }

    public void remove(Effect e) {
        // Implementation for removing an effect
    }

    @Override
    public boolean eat(Spore sp) {
        // Implementation for eating a spore
        return false;
    }

    @Override
    public boolean move(Tecton t) {
        // Implementation for moving to a Tecton
        return false;
    }

    @Override
    public boolean cut(MushroomThread th) {
        // Implementation for cutting a thread
        return false;
    }

    @Override
    public void endTurn() {
        // Implementation for ending the turn
    }

    @Override
    public void remove() {
        // Implementation for removing the insect
    }
}