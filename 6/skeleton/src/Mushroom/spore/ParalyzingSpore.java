package mushroom.spore;

import effect.*;

public class ParalyzingSpore extends Spore {
    public ParalyzingSpore() {
        nutrition = 10;
        effect = new ParalyzeEffect();
    }
}
