package tecton;

import core.Debug;
import mushroom.MushroomThread;

public class SingleThreadedTecton extends Tecton {
    @Override
    public boolean add(MushroomThread th) {
        
        if(threads.size() == 0){
            threads.add(th);
            Debug.DBGFUNC("added thread to SingleThreadedTecton");
            return true;
        }
        Debug.DBGFUNC("Can't add any more threads to this SingleThreadedTecton");
        return false;
    }
}
