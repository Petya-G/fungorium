package mushroom;

import java.util.List;

import mushroom.spore.*;
import core.Entity;
import tecton.Tecton;
import core.Debug;

public class MushroomStem extends Entity {
    private boolean thrown;
    private int maxSporeThrows;
    private int numThrownSpores;
    private int level;
    private int cost;

    /**
     * Konstruktor
     */
    public MushroomStem(Mushroomer owner, Tecton location) {
        this.owner = owner;
        this.location = location;
        this.maxSporeThrows = 5;
        this.numThrownSpores = 0;
        this.level = 0;
        this.thrown = false;
        this.cost = 3;
        Debug.DBGFUNC("Gomba létrehozva");
    }

    /**
     * Gombatest árának lekérése
     */
    public int getCost() {
        return cost;
    }

    /**
     * Spóra dobása, ahol a spóratípus a test szintjétől függ
     */
    public boolean throwSpore(Tecton tecton){
        Spore spore = new SpeedingSpore((Mushroomer)owner, tecton);
        if(level == 1)
            spore = new SlowingSpore((Mushroomer)owner, tecton);
        if(level == 2)
            spore = new ClawParalyzingSpore((Mushroomer)owner, tecton);
        if(level == 3)
            spore = new ParalyzingSpore((Mushroomer)owner, tecton);

        if(!thrown && tecton.add(spore)){
            ((Mushroomer)owner).add(spore);
            thrown = true;
            numThrownSpores++;

            if(numThrownSpores == maxSporeThrows)
                remove();

            Debug.DBGFUNC("Spore thrown successfully");
            return true;
        }
        Debug.DBGFUNC("Failed to throw spore (already thrown in turn)");
        return false;
    }

    /**
     * Test fejlesztése, amennyiben van spóra a tektonon
     */
    public boolean levelUp() {
        List<Spore> spores = location.getSpores(owner);
        if(spores.size() > 0){
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