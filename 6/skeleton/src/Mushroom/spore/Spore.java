package mushroom.spore;

import effect.*;

public abstract class Spore {
    int nutrition;
    Effect effect;

    public Effect getEffect(){
        return effect;
    }
}
