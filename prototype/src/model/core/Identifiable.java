package model.core;

import java.util.Objects;

public abstract class Identifiable {
    private static int sId = 0;
    private final int id = sId++;
    private final String name = getClass().getSimpleName() + "_" + id;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Identifiable that = (Identifiable) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}