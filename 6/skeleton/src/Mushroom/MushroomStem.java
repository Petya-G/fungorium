package mushroom;

import core.Entity;
import mushroom.spore.*;
import tecton.Tecton;

public class MushroomStem extends Entity {
    private int maxSporeThrows;
    private int numThrownSpores;
    private int level;

    public MushroomStem() {
        this.maxSporeThrows = 5;
        this.numThrownSpores = 0;
        this.level = 0;
    }

    public boolean throwSpore(Tecton tecton){
        Spore spore = new SpeedingSpore();
        if(level == 1)
            spore = new SlowingSpore();
        if(level == 2)
            spore = new ClawParalyzingSpore();
        if(level == 2)
            spore = new ParalyzingSpore();

        if(tecton.add(spore)){
            numThrownSpores++;
            if(numThrownSpores == maxSporeThrows)
                remove();
            return true;
        }
        return false;
    }

    public boolean levelUp() {
        throw new UnsupportedOperationException("Unimplemented method 'levelUp'");
    }

    public int getMaxSporeThrows() {
        return maxSporeThrows;
    }

    public int getNumThrownSpores() {
        return numThrownSpores;
    }

    @Override
    public void remove() {
        getLocation().remove(this);
        ((Mushroomer) getOwner()).remove(this);
    }

    @Override
    public void endTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endTurn'");
    }
}