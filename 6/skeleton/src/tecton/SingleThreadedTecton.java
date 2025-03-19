package tecton;

import mushroom.MushroomThread;

public class SingleThreadedTecton extends Tecton {
    @Override
    public boolean add(MushroomThread th) {
        return false;
    }
}
