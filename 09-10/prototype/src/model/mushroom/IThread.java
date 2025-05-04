package model.mushroom;

import java.util.List;

/**
 * Az IThread interfész egy gombafonal viselkedését definiálja.
 * Ez az interfész lehetővé teszi gombafonal hozzáadását és eltávolítását.
 */
public interface IThread {
    /**
     * Gombafonal hozzáadás.
     *
     * @param th A hozzáadandó gombafonal.
     * @return Igaz, ha a hozzáadás sikeres, egyébként hamis.
     */
    boolean add(MushroomThread th);

    /**
     * Eltávolít egy gombafonalat.
     *
     * @param th Az eltávolítandó gombafonal.
     * @return Igaz, ha az eltávolítás sikeres, egyébként hamis.
     */
    boolean remove(MushroomThread th);

    /**
     * Kezelt gombafonalak listáját adja vissza.
     * 
     * @return Gombafonalak objektumok listája. A lista üres is lehet, ha nincsenek gombafonalak.
     */
    List<MushroomThread> getThreads();

    /**
     * Megkeresi a megadott azonosítójú MushroomThread objektumot.
     *
     * @param id A keresett gombafonal egyedi azonosítója.
     * @return A megtalált MushroomThread példány, vagy null, ha nem található.
     */
    default MushroomThread getThread(int id) {
        return getThreads().stream()
                .filter(thread -> thread.getId() == id)
                .findFirst()
                .orElse(null);
    }
}