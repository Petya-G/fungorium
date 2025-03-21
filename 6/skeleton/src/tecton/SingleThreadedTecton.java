package tecton;

import mushroom.MushroomThread;

public class SingleThreadedTecton extends Tecton {
    @Override
    public boolean add(MushroomThread th) {
        if(threads.size() == 0){
            threads.add(th);
            return true;
        }
        return false;
    }
}
