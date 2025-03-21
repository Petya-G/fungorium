package mushroom;

import core.Entity;
import mushroom.spore.*;
import tecton.Tecton;

public class MushroomStem extends Entity {
    private boolean thrown;
    private int maxSporeThrows;
    private int numThrownSpores;
    private int level;

    public MushroomStem(Mushroomer owner, Tecton location) {
        this.owner = owner;
        this.location = location;
        this.maxSporeThrows = 5;
        this.numThrownSpores = 0;
        this.level = 0;
        this.thrown = false;
    }

    public boolean throwSpore(Tecton tecton){
        Spore spore = new SpeedingSpore((Mushroomer)owner, tecton);
        if(level == 1)
            spore = new SlowingSpore((Mushroomer)owner, tecton);
        if(level == 2)
            spore = new ClawParalyzingSpore((Mushroomer)owner, tecton);
        if(level == 3)
            spore = new ParalyzingSpore((Mushroomer)owner, tecton);

        if(tecton.add(spore)){
            ((Mushroomer)owner).add(spore);
            thrown = true;
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
        thrown = false;
    }
}