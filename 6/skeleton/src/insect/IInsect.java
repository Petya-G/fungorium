package insect;

import mushroom.spore.Spore;
import tecton.Tecton;
import mushroom.MushroomThread;

public interface IInsect {
    boolean eat(Spore sp);
    boolean move(Tecton t);
    boolean cut(MushroomThread th);
}