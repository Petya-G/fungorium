package mushroom;

import core.Debug;
import core.Entity;
import java.util.List;
import mushroom.spore.*;
import tecton.Tecton;

public class MushroomStem extends Entity {
    private boolean thrown;
    private int maxSporeThrows;
    private int numThrownSpores;
    private int level;
    private int cost;

    /**
     * Konstruktor
     *
     * @param owner    A gombatestet tulajdonló játékos
     * @param location A gombetest helye, vagyis, hogy melyik tektonon van
     */
    public MushroomStem(Tecton location, Mushroomer owner) {
        super(location, owner);
        this.maxSporeThrows = 5;
        this.numThrownSpores = 0;
        this.level = 0;
        this.thrown = false;
        this.cost = 3;
        Debug.DBGFUNC("Gomba létrehozva");
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
        Spore spore = new SpeedingSpore((Mushroomer) getOwner(), tecton);
        if (level == 1)
            spore = new SlowingSpore((Mushroomer) getOwner(), tecton);
        if (level == 2)
            spore = new ClawParalyzingSpore((Mushroomer) getOwner(), tecton);
        if (level == 3)
            spore = new ParalyzingSpore((Mushroomer) getOwner(), tecton);

        if (thrown || !tecton.add(spore)) {
            Debug.DBGFUNC("Failed to throw spore");
            return false;
        }

        ((Mushroomer) getOwner()).add(spore);
        thrown = true;
        numThrownSpores++;

        if (numThrownSpores == maxSporeThrows)
            remove();

        Debug.DBGFUNC("Spore thrown successfully");
        return true;
    }

    /**
     * Test fejlesztése, amennyiben van a játékosnak spórája a tektonon
     *
     * @return true, ha szintet, egyébként false
     */
    public boolean levelUp() {
        List<Spore> spores = ((Mushroomer) getOwner()).getSpores(getLocation());
        if (!spores.isEmpty()) {
            level++;
            spores.get(0).remove();
            Debug.DBGFUNC("Gomba szintet lép");
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
        Debug.DBGFUNC("GOmba törlése");
    }

    /**
     * Kör vége, ezután újra dobhatunk spórát
     */
    @Override
    public void endTurn() {
        thrown = false;
        Debug.DBGFUNC("Kör vége");
    }
}