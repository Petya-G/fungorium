package model.mushroom.spore;

import controller.visitor.GameObjectVisitor;
import model.core.Player;
import model.effect.ParalyzeEffect;
import model.tecton.Tecton;

/**
 * A {@code ParalyzingSpore} osztály egy speciális spóra típus, amely
 * bénító hatást fejt ki arra az objektumra, amelyet elér.
 *
 * A létrehozáskor automatikusan hozzárendel egy {@link ParalyzeEffect} hatást.
 */
public class ParalyzingSpore extends Spore {

    /**
     * Új bénító spóra létrehozása adott tulajdonossal és helyszínnel.
     *
     * @param owner a spóra tulajdonosa (játékos)
     * @param location a tecton, ahol a spóra elhelyezkedik
     */
    public ParalyzingSpore(Player owner, Tecton location) {
        super(owner, location);
        effect = new ParalyzeEffect();
    }

    /**
     * Új bénító spóra létrehozása adott tulajdonossal, helyszínnel és azonosítóval.
     *
     * @param owner a spóra tulajdonosa
     * @param location a tecton helyszín
     * @param id az egyedi azonosító
     */
    public ParalyzingSpore(Player owner, Tecton location, int id) {
        super(owner, location, id);
        effect = new ParalyzeEffect();
    }

    /**
     * Másoló konstruktor, amely egy meglévő bénító spóra alapján hoz létre újat.
     *
     * @param spore a másolandó spóra
     */
    public ParalyzingSpore(ParalyzingSpore spore) {
        super(spore);
    }

    /**
     * A látogató fogadása (Visitor minta szerint).
     *
     * @param visitor a látogató objektum
     */
    public void accept(GameObjectVisitor visitor) {
        visitor.visit(this);
    }
}
