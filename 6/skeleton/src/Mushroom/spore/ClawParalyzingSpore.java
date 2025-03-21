package mushroom.spore;

import effect.*;
import mushroom.Mushroomer;
import tecton.Tecton;

public class ClawParalyzingSpore extends Spore {

    public ClawParalyzingSpore(Mushroomer owner, Tecton location) {
        this.owner = owner;
        this.location = location;
        nutrition = 10;
        effect = new ClawParalyzeEffect();
    }

    @Override
    public void endTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endTurn'");
    }
}