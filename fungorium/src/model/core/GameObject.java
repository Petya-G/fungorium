package model.core;

import controller.visitor.GameObjectVisitor;

import java.util.Objects;

/**
 * Absztrakt osztály, amely egyedi azonosítóval lát el minden leszármazott objektumot.
 */
public abstract class GameObject {
    /**
     * Eddig használt legnagyob azonosító
     */
    private static int sId = 0;
    /**
     * Egyedi azonosító
     */
    private final int id;

    /**
     * Létrehoz egy példányt automatikusan generált azonosítóval.
     */
    protected GameObject() {
        this.id = sId++;
    }

    /**
     * Létrehoz egy példányt a megadott azonosítóval.
     *
     * @param id Az objektum egyedi azonosítója
     */
    protected GameObject(int id) {
        this.id = id;
    }

    protected GameObject(GameObject gameObject) {
        this.id = gameObject.id;
    }

    public static int getMaxId() {
        return sId;
    }

    public void accept(GameObjectVisitor visitor){}

    /**
     * Lekérdezi az objektum egyedi azonosítóját.
     *
     * @return Az azonosító értéke
     */
    public int getId() {
        return id;
    }

    /**
     * Visszaadja az objektum nevét, amely a típusából és azonosítójából áll.
     *
     * @return Az objektum neve
     */
    public String getName() {
        return getClass().getSimpleName() + "_" + id;
    }

    /**
     * Meghatározza, hogy két objektum azonosnak számít-e az azonosító alapján.
     *
     * @param o Az összehasonlítandó objektum
     * @return true, ha az objektumok azonosak, egyébként false
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return id == that.id;
    }

    /**
     * Az objektum hash-kódját adja vissza azonosító alapján.
     *
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