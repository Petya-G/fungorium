package model.mushroom;

import java.util.List;
import java.util.Objects;
import model.core.Entity;
import model.core.GlobalRandom;
import model.mushroom.spore.*;
import model.tecton.Tecton;

public class MushroomStem extends Entity {
    private final int maxSporeThrows = 5;
    private boolean thrown = false;
    private int numThrownSpores = 0;
    private int level = 0;
    private int cost = 3;

    //TODO cost az mire kell még? lehetne final
    private static final int  MAX_LEVEL = 2;

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
     * Spóra dobása, ahol a spóratípus random
     *
     * @param tecton A tekton, amire dobja a spórát
     * @return A művelet sikeressége (bool)
     */
    public boolean throwSpore(Tecton tecton) {
        int range = level + 1;
        if (getLocation().distanceTo(tecton) > range) return false;

        int rnd = GlobalRandom.getInstance().nextInt(5);
        Spore spore;

        switch (rnd) {
            case 0 -> spore = new SpeedingSpore((Mushroomer) getOwner(), tecton);
            case 1 -> spore = new SlowingSpore((Mushroomer) getOwner(), tecton);
            case 2 -> spore = new ClawParalyzingSpore((Mushroomer) getOwner(), tecton);
            case 3 -> spore = new ParalyzingSpore((Mushroomer) getOwner(), tecton);
            default -> spore = new SplitterSpore((Mushroomer) getOwner(), tecton);
        }

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
        if(level>MAX_LEVEL) return false;

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
     *@return  Maximum spóradobások száma
     */
    public int getMaxSporeThrows() {
        return maxSporeThrows;
    }

    /**
     * Eddigi spóradobások száma
     * @return  Eddigi spóradobások száma
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

    /**
     *Összehasonlít 2 objektumot
     * @param o    Objektum, amivel összehasonlítjuk
     *@return  Igaz, ha megegyezik a 2 objektum, egyébként hamis
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MushroomStem that = (MushroomStem) o;
        return thrown == that.thrown && maxSporeThrows == that.maxSporeThrows && numThrownSpores == that.numThrownSpores && level == that.level && cost == that.cost;
    }

    /**
     * Visszaadja az objektum hash-kódját, amely a jelenlegi példány mezői alapján kerül kiszámításra.
     * @return Az objektum hash-kódja.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), thrown, maxSporeThrows, numThrownSpores, level, cost);
    }
}