package tecton;

import mushroom.MushroomStem;

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
