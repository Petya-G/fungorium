package model.mushroom;

import java.util.List;

/**
 * Az IStem interfész egy gombatest viselkedését definiálja.
 * Ez az interfész lehetővé teszi gombatest hozzáadását és eltávolítását.
 */
public interface IStem {
    /**
     * Gombatest hozzáadás.
     *
     * @param ms A hozzáadandó gombatest.
     * @return Igaz, ha a hozzáadás sikeres, egyébként hamis.
     */
    boolean add(MushroomStem ms);

    /**
     * Eltávolít egy gombatestet.
     *
     * @param ms Az eltávolítandó gombatest.
     * @return Igaz, ha az eltávolítás sikeres, egyébként hamis.
     */
    boolean remove(MushroomStem ms);

    /**
     * Kezelt gombatestek listáját adja vissza.
     * 
     * @return Gombatest objektumok listája. A lista üres is lehet, ha nincsenek gombatestek.
     */
    List<MushroomStem> getStems();

    /**
     * Megkeresi a megadott azonosítójú MushroomStem objektumot.
     *
     * @param id A keresett gombatest egyedi azonosítója.
     * @return A megtalált MushroomStem példány, vagy null, ha nem található.
     */
    default MushroomStem getStem(int id){
        return getStems().stream()
                .filter(stem -> stem.getId() == id)
                .findFirst()
                .orElse(null);
    }
}