package model.mushroom.spore;

import controller.visitor.GameObjectVisitor;
import model.effect.SlowEffect;
import model.mushroom.Mushroomer;
import model.tecton.Tecton;

/**
 * A {@code SlowingSpore} osztály egy speciális spóra típus, amely
 * lassító hatást fejt ki a célpontra, például rovarokra.
 *
 * A spóra automatikusan hozzárendelt {@link SlowEffect} hatással rendelkezik.
 */
public class SlowingSpore extends Spore {

    /**
     * Új lassító spóra létrehozása adott tulajdonossal és helyszínnel.
     *
     * @param owner a spóra tulajdonosa (gombairányító)
     * @param location a tecton, ahol a spóra elhelyezkedik
     */
    public SlowingSpore(Mushroomer owner, Tecton location) {
        super(owner, location);
        effect = new SlowEffect();
    }

    /**
     * Új lassító spóra létrehozása adott tulajdonossal, helyszínnel és egyedi azonosítóval.
     *
     * @param owner a spóra tulajdonosa
     * @param location a tecton, ahol elhelyezkedik
     * @param id az egyedi azonosító
     */
    public SlowingSpore(Mushroomer owner, Tecton location, int id) {
        super(owner, location, id);
        effect = new SlowEffect();
    }

    /**
     * Másoló konstruktor – létrehoz egy új lassító spórát egy meglévő példány alapján.
     *
     * @param spore a másolandó spóra
     */
    public SlowingSpore(SlowingSpore spore) {
        super(spore);
    }

    /**
     * A látogató fogadása a Visitor minta alapján.
     *
     * @param visitor a látogatót reprezentáló objektum
     */
    public void accept(GameObjectVisitor visitor) {
        visitor.visit(this);
    }
}
