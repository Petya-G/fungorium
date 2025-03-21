package core;

import tecton.Tecton;

public abstract class Entity implements ITurn {
    protected Tecton location;
    protected Player owner;

    public Tecton getLocation() {
        return location;
    }

    public abstract void remove();

    @Override
    public abstract void endTurn();
}