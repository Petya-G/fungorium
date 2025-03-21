package mushroom.spore;

import effect.*;
import mushroom.Mushroomer;
import tecton.Tecton;

public class ParalyzingSpore extends Spore {
    public ParalyzingSpore(Mushroomer owner, Tecton location) {
        this.owner = owner;
        this.location = location;
        nutrition = 10;
        effect = new ParalyzeEffect();
    }

    @Override
    public void endTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endTurn'");
    }
}
