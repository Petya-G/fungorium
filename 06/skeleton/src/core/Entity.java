package core;

import tecton.Tecton;

public abstract class Entity implements ITurn {
    protected Tecton location;
    protected Player owner;

    public abstract void remove();

    /**
     * Visszaadja az entitást birtokló játékost
     * @return Az entitást birtokló játékos
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Visszaadja az entitás helyét
     * @return A tekton, amin az entitás van
     */
    public Tecton getLocation() {
        return location;
    }

    /**
     * Beállítja az entitás helyét
     * @param location A tekton, amin az entitás van
     */
    public void setLocation(Tecton location) {
        this.location = location;
    }

    /**
     * Minden kör végén lefut
     */
    public void endTurn(){
        System.out.println("Kör vége");
    }
}