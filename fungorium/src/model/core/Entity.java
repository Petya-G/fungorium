package model.core;

import model.tecton.Tecton;

import java.io.Serializable;
import java.util.Objects;

/**
 * Absztrakt osztály, amely egy játékbeli entitást reprezentál
 */
public abstract class Entity extends GameObject implements ITurn, Serializable {
    /**
     * Az entitás tulajdonosa.
     */
    private final Player owner;
    /**
     * Az entitás jelenlegi helye.
     */
    private Tecton location;

    /**
     * Létrehoz egy új entitást megadott tulajdonossal és helyszínnel.
     *
     * @param owner    Az entitás tulajdonosa.
     * @param location Az entitás helye (Tecton).
     */
    protected Entity(Player owner, Tecton location) {
        this.owner = owner;
        this.location = location;
    }

    /**
     * Létrehoz egy új entitást adott azonosítóval, tulajdonossal és helyszínnel.
     *
     * @param owner    Az entitás tulajdonosa.
     * @param location Az entitás helye (Tecton).
     * @param id       Az entitás egyedi azonosítója.
     */
    protected Entity(Player owner, Tecton location, int id) {
        super(id);
        this.owner = owner;
        this.location = location;
    }

    protected Entity(Entity entity) {
        super(entity);
        this.owner = entity.getOwner();
        this.location = entity.getLocation();
    }

    /**
     * Az entitás eltávolítását végző absztrakt metódus.
     * Minden konkrét entitás típusnak implementálnia kell.
     */
    public abstract void remove();

    /**
     * Visszaadja az entitást birtokló játékost.
     *
     * @return Az entitás tulajdonosa (Player).
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Visszaadja az entitás aktuális helyét.
     *
     * @return A Tecton objektum, ahol az entitás található.
     */
    public Tecton getLocation() {
        return location;
    }

    /**
     * Beállítja az entitás új helyét.
     *
     * @param location Az új Tecton hely.
     */
    public void setLocation(Tecton location) {
        this.location = location;
    }

    /**
     * Az entitás körének lezárásakor meghívandó metódus.
     */
    public void endTurn() {
    }

    /**
     * Két entitás egyenlőségét vizsgálja azok azonosítóin, tulajdonosán és helyzetén alapulva.
     *
     * @param o Az összehasonlítandó objektum.
     * @return Igaz, ha az entitások megegyeznek, különben hamis.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Entity entity = (Entity) o;
        return Objects.equals(location, entity.location) && Objects.equals(owner, entity.owner);
    }

    /**
     * Az entitás hash kódját adja vissza, amely azonosítón, helyen és tulajdonoson alapszik.
     *
     * @return Az entitás hash kódja.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        return super.toString() + " location=" + location.getName() + ", owner=" + owner.getName();
    }
}