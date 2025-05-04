package model.tecton;

import model.mushroom.MushroomStem;

/**
 * Olyan tekton fajta, amin nem lehet gombatest.
 */
public class StemlessTecton extends Tecton {

    /**
     * Rárakna egy gombatestet a tektonra, de ilyen tektonra nem lehet
     *
     * @param ms A rárakandó gombatest
     * @return false, mert nem lehet
     */
    @Override
    public boolean add(MushroomStem ms) {
        return false;
    }
}
