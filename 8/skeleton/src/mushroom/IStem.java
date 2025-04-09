package mushroom;

/**
 * Az IStem interfész egy gombatest viselkedését definiálja.
 * Ez az interfész lehetővé teszi gombatest hozzáadását és eltávolítását.
 */
public interface IStem {
    /**
     * Gombatest hozzáadás.
     * @param ms A hozzáadandó gombatest.
     * @return Igaz, ha a hozzáadás sikeres, egyébként hamis.
     */
    boolean add(MushroomStem ms);

    /**
     * Eltávolít egy gombatestet.
     * @param ms Az eltávolítandó gombatest.
     * @return Igaz, ha az eltávolítás sikeres, egyébként hamis.
     */
    boolean remove(MushroomStem ms);
}