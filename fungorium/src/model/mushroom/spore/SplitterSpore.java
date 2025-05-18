package model.mushroom.spore;

import controller.visitor.GameObjectVisitor;
import model.effect.SplitEffect;
import model.mushroom.Mushroomer;
import model.tecton.Tecton;

/**
 * A {@code SplitterSpore} egy olyan spóra típus, amely osztódó hatást fejt ki.
 *
 * A célpontra kifejtett hatás a {@link SplitEffect}, amely valamilyen formában szétválást vagy másolódást idézhet elő.
 */
public class SplitterSpore extends Spore {

    /**
     * Új osztódó spóra példány létrehozása egy adott tulajdonossal és helyszínnel.
     *
     * @param owner a spóra tulajdonosa (gombás játékos)
     * @param location a tecton, ahol a spóra elhelyezkedik
     */
    public SplitterSpore(Mushroomer owner, Tecton location) {
        super(owner, location);
        effect = new SplitEffect();
    }

    /**
     * Új osztódó spóra létrehozása egyedi azonosítóval.
     *
     * @param owner a spóra tulajdonosa
     * @param location a tecton, ahol a spóra van
     * @param id az egyedi azonosító
     */
    public SplitterSpore(Mushroomer owner, Tecton location, int id) {
        super(owner, location, id);
        effect = new SplitEffect();
    }

    /**
     * Másoló konstruktor – új példány létrehozása meglévő spóra alapján.
     *
     * @param spore a másolandó spóra
     */
    public SplitterSpore(SplitterSpore spore) {
        super(spore);
    }

    /**
     * A látogató fogadása a Visitor minta szerint.
     *
     * @param visitor a látogató objektum
     */
    public void accept(GameObjectVisitor visitor) {
        visitor.visit(this);
    }
}
