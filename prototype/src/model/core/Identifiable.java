package model.core;

public abstract class Identifiable {
    private final long id;

    protected Identifiable() {
        id = 1;
    }

    public long getId() {
        return id;
    }
}