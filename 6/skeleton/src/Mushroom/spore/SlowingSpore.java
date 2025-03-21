package mushroom.spore;

import effect.*;

public class SlowingSpore extends Spore {
    public SlowingSpore() {
        nutrition = 10;
        effect = new SlowEffect();
    }
}
