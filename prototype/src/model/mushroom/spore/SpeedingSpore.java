package model.mushroom.spore;

import model.mushroom.Mushroomer;
import model.core.Debug;
import model.effect.*;
import model.tecton.Tecton;

public class SpeedingSpore extends Spore {

    /**
     * Konstruktor, beállítja a spóra owner-ét (a játékos, akihez tartozik), a
     * helyét (a tekton, amin van), a tapértékét, illetve a rovarokra gyakorolt
     * hatását
     *
     * @param owner    A spórát tulajdonló játékos
     * @param location A tekton, amin a spóra van
     */
    public SpeedingSpore(Mushroomer owner, Tecton location) {
        super(location, owner, 10, new FastEffect());
        Debug.DBGFUNC("Gyorsító spóra lerakva");
    }
}
