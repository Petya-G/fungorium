package model.mushroom.spore;

import controller.visitor.GameObjectVisitor;
import model.effect.FastEffect;
import model.mushroom.Mushroomer;
import model.tecton.Tecton;

/**
 * A {@code SpeedingSpore} osztály egy olyan spórát reprezentál,
 * amely gyorsító hatást fejt ki a célpontra (pl. rovarokra).
 *
 * Automatikusan hozzárendel egy {@link FastEffect} típusú hatást.
 */
public class SpeedingSpore extends Spore {

    /**
     * Gyorsító spóra létrehozása egy tulajdonossal és elhelyezkedéssel.
     *
     * @param owner a spóra tulajdonosa (gombás játékos)
     * @param location a tecton, ahol a spóra megjelenik
     */
    public SpeedingSpore(Mushroomer owner, Tecton location) {
        super(owner, location);
        effect = new FastEffect();
    }

    /**
     * Gyorsító spóra létrehozása megadott azonosítóval.
     *
     * @param owner a spóra tulajdonosa
     * @param location a spóra pozíciója (tecton)
     * @param id az egyedi azonosító
     */
    public SpeedingSpore(Mushroomer owner, Tecton location, int id) {
        super(owner, location, id);
        effect = new FastEffect();
    }

    /**
     * Másoló konstruktor: új spóra létrehozása egy meglévő példány alapján.
     *
     * @param spore a másolandó spóra példány
     */
    public SpeedingSpore(SpeedingSpore spore) {
        super(spore);
    }

    /**
     * Látogató fogadása a Visitor minta szerint.
     *
     * @param visitor a látogató objektum
     */
    public void accept(GameObjectVisitor visitor) {
        visitor.visit(this);
    }
}
