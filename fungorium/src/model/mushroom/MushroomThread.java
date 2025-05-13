package model.mushroom;

import controller.GameObjectVisitor;
import java.util.*;
import model.core.Entity;
import model.insect.Insect;
import model.tecton.Tecton;

public class MushroomThread extends Entity {

    private static final int MAX_CUTOFF_DURATION = 2;
    private boolean eaten = false;
    private boolean cutOff = false;
    private int cutOffDuration = 0;

    /**
     * Konstruktor
     *
     * @param owner    A gombafonalat tulajdonló gombász
     * @param location A tekton, amin a gombafonál van
     */
    public MushroomThread(Mushroomer owner, Tecton location) {
        super(owner, location);
    }

    /**
     * Megmondja, hogy a gombafonal evett-e rovart a körben
     *
     * @return {@code true}, hogyha evett, egyébként {@code false}.
     */
    public boolean hasEaten() {
        return eaten;
    }

    /**
     * Állíthatjuk vele az értéket, ami megmondja, hogy a fonal evett-e rovart a körben
     *
     * @param b Amire állítjuk az értéket
     */
    public void setEaten(boolean b) {
        this.eaten = b;
    }

    /**
     * Megadja, hogy a gombafonal le van-e választva a többi gombásztól (pl. ha megszűnt a kapcsolata).
     *
     * @return {@code true}, ha a gombafonal leválasztott állapotban van, különben {@code false}.
     */
    public boolean isCutOff() {
        return cutOff;
    }

    /**
     * Állíthatjuk vele az értéket, ami megmondja, hogy a fonal kapcsolódik-e gombateshez
     *
     * @param b Amire állítjuk az értéket
     */
    public void setCutOff(boolean b) {
        this.cutOff = b;
    }

    /**
     * Visszaadja, hogy a gombafonal hány körön keresztül volt levágva.
     *
     * @return A levágottság ideje körökben mérve.
     */
    public int getCutOffDuration() {
        return cutOffDuration;
    }

    /**
     * Visszaadja, hogy legfeljebb hány körön át maradhat levágott állapotban a gombafonal.
     *
     * @return A maximális levágottsági idő körökben.
     */
    public int getMaxCutOffDuration() {
        return MAX_CUTOFF_DURATION;
    }

    /**
     * Segítségével a gombafonal megpróbálhat megenni egy paralyzed rovart, ha nincs leválasztva a többi fonaltól.
     *
     * @param insect Rovar, amit próbál megenni a gombafonal
     * @return {@code true}, hogyha meg tudta enni a rovart, egyébként {@code false}
     */
    public boolean eat(Insect insect) {
        if (!cutOff && insect.isParalyzed()) {
            insect.remove();
            eaten = true;
            return true;
        }
        return false;
    }

    /**
     * Megmondja, hogy a gombafonal kapcsolódik-e gombatesthez
     *
     * @return {@code true}, hogyha kapcsolódik, egyébként {@code false}.
     */
    public boolean isConnected() {
        Set<Tecton> visited = new HashSet<>();
        Queue<Tecton> queue = new LinkedList<>();

        Tecton start = getLocation();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Tecton current = queue.poll();

            if (current.getStems().stream().anyMatch(s -> s.getOwner().equals(getOwner()))) return true;

            for (Tecton t : current.getConnectedNeighbours((Mushroomer) getOwner())) {
                if (!visited.contains(t)) {
                    visited.add(t);
                    queue.add(t);
                }
            }
        }

        return false;
    }

    /**
     * Megvizsgálja, hogy van-e a megadott Tecton-on legalább egy érvényes MushroomThread}.
     *
     * @param tecton A vizsgált Tecton.
     * @return true, ha van rajta legalább egy nem "eaten" és nem "cutOff" gombafonál, különben false.
     */
    public boolean hasValidThread(Tecton tecton) {
        for (MushroomThread thread : tecton.getThreads()) {
            if (!thread.hasEaten() && !thread.cutOff) {
                return true;
            }
        }
        return false;
    }

    /**
     * Thread törlése a pályáról
     */
    @Override
    public void remove() {
        if (getLocation() != null) {
            getLocation().remove(this);
        }
        ((Mushroomer) getOwner()).remove(this);
    }

    /**
     * Kör vége
     */
    @Override
    public void endTurn() {
        if (cutOff) {
            cutOffDuration++;
            if (cutOffDuration == MAX_CUTOFF_DURATION) {
                remove();
            }
        }
    }

    @Override
    public void accept(GameObjectVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Összehasonlít 2 objektumot
     *
     * @param o Objektum, amivel összehasonlítjuk
     * @return {@code true}, ha megegyezik a 2 objektum, egyébként {@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MushroomThread that = (MushroomThread) o;
        return eaten == that.eaten && cutOff == that.cutOff && cutOffDuration == that.cutOffDuration;
    }

    /**
     * Visszaadja az objektum hash-kódját, amely a jelenlegi példány mezői alapján kerül kiszámításra.
     *
     * @return Az objektum hash-kódja.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), eaten, cutOff, cutOffDuration);
    }

    @Override
    public String toString() {
        return super.toString() + "eaten=" + eaten + ", cutOff=" + cutOff + ", cutOffDuration=" + cutOffDuration;
    }
}
