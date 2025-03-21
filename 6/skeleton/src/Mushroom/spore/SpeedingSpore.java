package mushroom.spore;

import effect.*;

public class SpeedingSpore extends Spore {
    public SpeedingSpore() {
        nutrition = 10;
        effect = new FastEffect();
    }
}
