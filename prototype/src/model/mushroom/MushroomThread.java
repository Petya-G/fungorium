package model.mushroom;

import model.core.Entity;
import model.insect.Insect;
import model.tecton.Tecton;

import java.util.Objects;

public class MushroomThread extends Entity {
    private boolean eaten;
    private boolean cutOff;
    private int cutOffDuration;
    private int maxCutOffDuration;

    /**
     * Konstruktor
     *
     * @param owner    A gombafonalat tulajdonló gombász
     * @param location A tekton, amin a gombafonál van
     */
    public MushroomThread(Tecton location, Mushroomer owner) {
        super(location, owner);
        eaten = false;
        cutOff = false;
        cutOffDuration = 0;
        maxCutOffDuration = 2;
    }

    public boolean isConnected() {
        // TODO: van-e gombatest melyhez csatlakozunk?
        return true;
    }

    public boolean hasEaten() {
        return eaten;
    }

    public void setEaten(boolean b) {
        this.eaten = b;
    }

    public void setCutoff(boolean b) {
        this.cutOff = b;
    }

    public boolean eat(Insect insect) {
        if (insect.isParalyzed()) {
            insect.remove();
            eaten = true;
            return true;
        }
        return false;
    }

    /**
     * Thread törlése a pályáról
     */
    @Override
    public void remove() {
        getLocation().remove(this);
        ((Mushroomer) getOwner()).remove(this);
    }

    /**
     * Kör vége
     */
    @Override
    public void endTurn() {
        if (cutOff) {
            cutOffDuration++;
            if (cutOffDuration == maxCutOffDuration) {
                remove();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MushroomThread that = (MushroomThread) o;
        return eaten == that.eaten && cutOff == that.cutOff && cutOffDuration == that.cutOffDuration && maxCutOffDuration == that.maxCutOffDuration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), eaten, cutOff, cutOffDuration, maxCutOffDuration);
    }
    /**
     * Megvizsgálja, hogy van-e a megadott {@code Tecton}-on legalább egy érvényes {@code MushroomThread}.
     *
     * @param tecton A vizsgált {@code Tecton}.
     * @return {@code true}, ha van rajta legalább egy nem "eaten" és nem "cutOff" gombafonál, különben {@code false}.
     */
    public boolean hasValidThread(Tecton tecton) {
        for (MushroomThread thread : tecton.getThreads()) {
            if (!thread.hasEaten()) {
                return true;
            }
        }
        return false;
    }
}
