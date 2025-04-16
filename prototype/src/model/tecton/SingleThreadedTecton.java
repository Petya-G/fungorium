package model.tecton;

import model.mushroom.MushroomThread;

public class SingleThreadedTecton extends Tecton {

    /**
     * A SingleThreadedTecton-ra rárak egy gomba fonalat, az ilyen tektonon
     * legfeljebb 1db gombafonál lehet
     *
     * @param th Az elhelyezendő gombafonál
     * @return Az add sikeressége
     */
    @Override
    public boolean add(MushroomThread th) {

        if (threads.size() == 0) {
            threads.add(th);
            return true;
        }
        return false;
    }
}
