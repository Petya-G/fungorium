package mushroom.spore;

import effect.Effect;

public class SpeedingSpore extends Spore{
    public Effect getEffect(){
        System.out.println("Speeding Spore");
        return effect;
    }
}
