package model.mushroom;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import model.core.Entity;
import model.insect.Insect;
import model.tecton.Tecton;

public class MushroomThread extends Entity {
    
    private boolean eaten = false;
    private boolean cutOff = false;
    private int cutOffDuration = 0;
    
    private final int MAX_CUTOFF_DURATION = 2;

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
     * Megmondja, hogy a gombafonal kapcsolódik-e gombatesthez
     *
     * @return Igaz, hogyha kapcsolódik, egyébként hamis.
     */
    public boolean isConnected() {
        Set<Tecton> visited = new HashSet<>();
        Queue<Tecton> queue = new LinkedList<>();

        Tecton start = getLocation();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Tecton current = queue.poll();

            if (current.hasStem()) return true;

            List<Tecton> connectedNeighbourList=current.getConnectedNeighbours((Mushroomer)getOwner());

            for (Tecton t : connectedNeighbourList) {
                if (visited.contains(t)) continue;
                if(!visited.contains(t)){
                    visited.add(t);
                    queue.add(t);
                }
            }
        }

        return false;
    }

    /**
     * Megmondja, hogy a gombafonal evett-e rovart a körben
     *
     * @return Igaz, hogyha evett, egyébként hamis.
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
     * Állíthatjuk vele az értéket, ami megmondja, hogy a fonal kapcsolódik-e gombateshez
     *
     * @param b Amire állítjuk az értéket
     */
    public void setCutoff(boolean b) {
        this.cutOff = b;
    }

    /**
     * Segítségével a gombafonal megpróbálhat megenni egy rovart
     *
     * @param insect Rovar, amit próbál megenni a gombafonal
     * @return Igaz, hogyha meg tudta enni a rovart, egyébként hamis
     */
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
            if (cutOffDuration == MAX_CUTOFF_DURATION) {
                remove();
            }
        }
    }

    /**
     *Összehasonlít 2 objektumot
     * @param o    Objektum, amivel összehasonlítjuk
     *@return  Igaz, ha megegyezik a 2 objektum, egyébként hamis
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MushroomThread that = (MushroomThread) o;
        return eaten == that.eaten && cutOff == that.cutOff && cutOffDuration == that.cutOffDuration && MAX_CUTOFF_DURATION == that.MAX_CUTOFF_DURATION;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), eaten, cutOff, cutOffDuration, MAX_CUTOFF_DURATION);
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
