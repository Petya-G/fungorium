package mushroom.spore;

import effect.*;

public class ClawParalyzingSpore extends Spore {

    public ClawParalyzingSpore() {
        nutrition = 10;
        effect = new ClawParalyzeEffect();
    }
}