package Mushroom.spore;

import Mushroom.Mushroomer;
import effect.*;
import tecton.Tecton;

public class ClawParalyzingSpore extends Spore {

    public ClawParalyzingSpore(Mushroomer owner, Tecton location) {
        this.owner = owner;
        this.location = location;
        nutrition = 10;
        effect = new ClawParalyzeEffect();
    }
}