package insect;

import mushroom.MushroomThread;
import mushroom.spore.Spore;
import tecton.Tecton;

public interface IInsect {
    boolean eat(Spore sp);
    boolean move(Tecton t);
    boolean cut(MushroomThread th);
}