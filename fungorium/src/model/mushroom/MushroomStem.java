package model.mushroom;

import controller.visitor.GameObjectVisitor;
import java.util.List;
import java.util.Objects;
import model.Game;
import model.core.Entity;
import model.mushroom.spore.*;
import model.tecton.Tecton;

public class MushroomStem extends Entity {

    private boolean thrown = false;
    private int numThrownSpores = 0;
    private int level = 0;

    private static final int LEVEL_UP_COST = 3;
    private static final int MAX_LEVEL = 2;
    private static final int MAX_SPORE_THROWS = 5;

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
     * Megadja, hogy a gombatest az adott körben dobott-e már spórát.
     *
     * @return {@code true}, ha már dobott spórát ebben a körben, különben {@code false}.
     */
    public boolean hasThrown() {
        return thrown;
    }

    /**
     * Eddigi spóradobások száma
     *
     * @return Eddigi spóradobások száma
     */
    public int getNumThrownSpores() {
        return numThrownSpores;
    }

    /**
     * Visszaadja a gombatest aktuális szintjét.
     *
     * @return A gombatest szintje (0-tól indul, legfeljebb 2).
     */
    public int getLevel() {
        return level;
    }


    /**
     * Gombatest árának lekérése
     *
     * @return A gombatest ára
     */
    public int getLevelUpCost() {
        return LEVEL_UP_COST;
    }

    /**
     * Visszaadja a gombatest elérhető maximális szintjét.
     *
     * @return A maximálisan elérhető szint.
     */
    public int getMaxLevel() {
        return MAX_LEVEL;
    }

    /**
     * Maximum spóradobások száma
     *
     * @return Maximum spóradobások száma
     */
    public int getMaxSporeThrows() {
        return MAX_SPORE_THROWS;
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

        int rnd = Game.random.nextInt(5);
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

        if (numThrownSpores == MAX_SPORE_THROWS)
            remove();

        return true;
    }

    /**
     * Test fejlesztése, amennyiben van a játékosnak spórája a tektonon
     *
     * @return true, ha szintet lép, egyébként false
     */
    public boolean levelUp() {
        if (level >= MAX_LEVEL) return false;

        List<Spore> spores = ((Mushroomer) getOwner()).getSpores(getLocation());

        if (spores.size() >= LEVEL_UP_COST) {
            level++;
            for (int i = 0; i < LEVEL_UP_COST; i++) {
                spores.get(i).remove();
            }
            return true;
        }

        return false;
    }


    /**
     * Stem törlése a játékból
     */
    @Override
    public void remove() {
        if (getLocation() != null) {
            getLocation().remove(this);
        }
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
        MushroomStem that = (MushroomStem) o;
        return thrown == that.thrown && numThrownSpores == that.numThrownSpores && level == that.level;
    }

    /**
     * Visszaadja az objektum hash-kódját, amely a jelenlegi példány mezői alapján kerül kiszámításra.
     *
     * @return Az objektum hash-kódja.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), thrown, numThrownSpores, level);
    }

    @Override
    public String toString() {
        return super.toString()
                + "thrown=" + thrown +
                ", numThrownSpores=" + numThrownSpores +
                ", level=" + level;
    }
}