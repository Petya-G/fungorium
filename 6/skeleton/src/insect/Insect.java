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
    protected double speedModifier;

    public boolean isParalyzed() {
        return paralyzed;
    }

    public void setParalyzed(boolean paralyzed) {
        this.paralyzed = paralyzed;
    }

    public boolean isClawParalyzed() {
        return clawParalyzed;
    }

    public void setClawParalyzed(boolean clawParalyzed) {
        this.clawParalyzed = clawParalyzed;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public void setSpeedModifier(double speedModifier) {
        this.speedModifier = speedModifier;
    }

    public void remove(Effect e) {
        effects.remove(e);
    }

    public void add(Effect e) {
        effects.add(e);
    }

    @Override
    public boolean eat(Spore sp) {
        // TODO Auto-generated method stub: Implement method 'eat' 
        throw new UnsupportedOperationException("Unimplemented method 'eat'");
    }

    @Override
    public boolean move(Tecton t) {
        // TODO Auto-generated method stub: Implement method 'move' 
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public boolean cut(MushroomThread th) {
        // TODO Auto-generated method stub: Implement method 'eat' 
        throw new UnsupportedOperationException("Unimplemented method 'cut'");
    }

    @Override
    public void endTurn() {
        // TODO Auto-generated method stub: Implement method 'endturn' 
        throw new UnsupportedOperationException("Unimplemented method 'endTurn'");
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub: Implement method 'remove' 
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
}