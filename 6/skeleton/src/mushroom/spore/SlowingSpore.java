package mushroom.spore;

import mushroom.Mushroomer;
import effect.*;
import tecton.Tecton;

public class SlowingSpore extends Spore {

    public SlowingSpore(Mushroomer owner, Tecton location) {
        this.owner = owner;
        this.location = location;
        nutrition = 10;
        effect = new SlowEffect();
    }
}
