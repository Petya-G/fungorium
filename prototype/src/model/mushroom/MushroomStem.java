package model.mushroom;

import java.util.List;
import java.util.Objects;
import model.core.Entity;
import model.mushroom.spore.*;
import model.tecton.Tecton;

public class MushroomStem extends Entity {
    private final int maxSporeThrows = 5;
    private boolean thrown = false;
    private int numThrownSpores = 0;
    private int level = 0;
    private int cost = 3;

    /**
     * Konstruktor
     *
     * @param owner    A gombatestet tulajdonló játékos
     * @param location A gombetest helye, vagyis, hogy melyik tektonon van
     */
    public MushroomStem(Mushroomer owner, Tecton location) {
        super(owner, location);
    }

    /**
     * Gombatest árának lekérése
     *
     * @return A gombatest ára
     */
    public int getCost() {
        return cost;
    }

    /**
     * Spóra dobása, ahol a spóratípus a test szintjétől függ
     *
     * @param tecton A tekton, amire dobja a spórát
     * @return A művelet sikeressége (bool)
     */
    public boolean throwSpore(Tecton tecton) {
        // TODO implement range check
        // TODO make spore throws random?
        // TODO cap mushroomstem max level ?
        Spore spore = new SpeedingSpore((Mushroomer) getOwner(), tecton);
        if (level == 1)
            spore = new SlowingSpore((Mushroomer) getOwner(), tecton);
        if (level == 2)
            spore = new ClawParalyzingSpore((Mushroomer) getOwner(), tecton);
        if (level == 3)
            spore = new ParalyzingSpore((Mushroomer) getOwner(), tecton);

        if (thrown || !tecton.add(spore)) {
            return false;
        }

        ((Mushroomer) getOwner()).add(spore);
        thrown = true;
        numThrownSpores++;

        if (numThrownSpores == maxSporeThrows)
            remove();

        return true;
    }

    /**
     * Test fejlesztése, amennyiben van a játékosnak spórája a tektonon
     *
     * @return true, ha szintet lép, egyébként false
     */
    public boolean levelUp() {
        //TODO megbeszélni
        List<Spore> spores = ((Mushroomer) getOwner()).getSpores(getLocation());
        if (!spores.isEmpty()) {
            level++;
            spores.get(0).remove();
            return true;
        }
        return false;
    }

    /**
     * Maximum spóradobások száma
     */
    public int getMaxSporeThrows() {
        return maxSporeThrows;
    }

    /**
     * Eddigi spóradobások száma
     */
    public int getNumThrownSpores() {
        return numThrownSpores;
    }

    /**
     * Stem törlése a játékból
     */
    @Override
    public void remove() {
        getLocation().remove(this);
        ((Mushroomer) getOwner()).remove(this);
    }

    /**
     * Kör vége, ezután újra dobhatunk spórát
     */
    @Override
    public void endTurn() {
        thrown = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MushroomStem that = (MushroomStem) o;
        return thrown == that.thrown && maxSporeThrows == that.maxSporeThrows && numThrownSpores == that.numThrownSpores && level == that.level && cost == that.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), thrown, maxSporeThrows, numThrownSpores, level, cost);
    }
}