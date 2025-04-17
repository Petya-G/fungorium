package model.core;

import model.tecton.Tecton;

import java.util.Objects;

public abstract class Entity extends Identifiable implements ITurn {
    private final Player owner;
    private Tecton location;

    protected Entity(Player owner, Tecton location) {
        this.owner = owner;
        this.location = location;
    }

    protected Entity(Player owner, Tecton location, int id) {
        super(id);
        this.owner = owner;
        this.location = location;
    }

    public abstract void remove();

    /**
     * Visszaadja az entitást birtokló játékost
     *
     * @return Az entitást birtokló játékos
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Visszaadja az entitás helyét
     *
     * @return A tekton, amin az entitás van
     */
    public Tecton getLocation() {
        return location;
    }

    /**
     * Beállítja az entitás helyét
     *
     * @param location A tekton, amin az entitás van
     */
    public void setLocation(Tecton location) {
        this.location = location;
    }

    /**
     * Minden kör végén lefut
     */
    public void endTurn() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Entity entity = (Entity) o;
        return Objects.equals(location, entity.location) && Objects.equals(owner, entity.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), location, owner);
    }
}