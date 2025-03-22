package mushroom.spore;

import mushroom.Mushroomer;
import core.Debug;
import effect.*;
import tecton.Tecton;

public class SpeedingSpore extends Spore {

    public SpeedingSpore(Mushroomer owner, Tecton location) {
        this.owner = owner;
        this.location = location;
        nutrition = 10;
        effect = new FastEffect();
        Debug.DBGFUNC("Gyorsító spóra lerakva");
    }
}
