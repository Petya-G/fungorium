package model.core;

import java.util.HashMap;
import java.util.Objects;

/**
 * Absztrakt osztály, amely egyedi azonosítóval lát el minden leszármazott objektumot.
 */
public abstract class Identifiable {
    /**
     * Eddig használt legnagyob azonosító
     */
    private static int sId = 0;
    //private static HashMap<Integer, Identifiable> objects = new HashMap();
    /**
     * Egyedi azonosító
     */
    private final int id;

    /**
     * Létrehoz egy példányt automatikusan generált azonosítóval.
     */
    protected Identifiable() {
        this.id = sId++;
        //objects.put(this.id, this);
    }

    /**
     * Létrehoz egy példányt a megadott azonosítóval.
     *
     * @param id Az objektum egyedi azonosítója
     */
    protected Identifiable(int id) {
        this.id = id;
       // objects.put(this.id, this);
    }

    protected Identifiable(Identifiable identifiable) {
        this.id = identifiable.id;
        //objects.put(this.id, this);
    }

    /**
     * Lekérdezi az objektum egyedi azonosítóját.
     * @return Az azonosító értéke
     */
    public int getId() {
        return id;
    }

    public static int getMaxId() {
        return sId;
    }

    /*public static Identifiable findObject(Integer id) {
        return objects.get(id);
    }*/

    /**
     * Visszaadja az objektum nevét, amely a típusából és azonosítójából áll.
     * @return Az objektum neve
     */
    public String getName() {
        return getClass().getSimpleName() + "_" + id;
    }

    /**
     * Meghatározza, hogy két objektum azonosnak számít-e az azonosító alapján.
     * @param o Az összehasonlítandó objektum
     * @return true, ha az objektumok azonosak, egyébként false
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Identifiable that = (Identifiable) o;
        return id == that.id;
    }

    /**
     * Az objektum hash-kódját adja vissza azonosító alapján.
     * @return Hash-kód érték
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getName();
    }
}