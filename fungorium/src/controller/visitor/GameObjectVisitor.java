package controller.visitor;

import model.core.GameObject;
import model.insect.Insect;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.spore.ClawParalyzingSpore;
import model.mushroom.spore.ParalyzingSpore;
import model.mushroom.spore.SlowingSpore;
import model.mushroom.spore.SpeedingSpore;
import model.mushroom.spore.SplitterSpore;
import model.tecton.*;

/**
 * A látogató (Visitor) minta interfésze, amely lehetővé teszi
 * különböző típusú játékelemek meglátogatását és kezelését.
 *
 * Ez az interfész alapértelmezett (üres) implementációkat biztosít,
 * így csak a szükséges metódusokat kell felüldefiniálni.
 */
public interface GameObjectVisitor {

    /**
     * Általános játékelem meglátogatása.
     *
     * @param gameObject a meglátogatott játékelem
     */
    default void visit(GameObject gameObject) {}

    /**
     * Életfenntartó tecton meglátogatása.
     *
     * @param lifeSupportTecton a meglátogatott objektum
     */
    default void visit(LifeSupportTecton lifeSupportTecton) {}

    /**
     * Egy szálon futó tecton meglátogatása.
     *
     * @param singleThreadedTecton a meglátogatott objektum
     */
    default void visit(SingleThreadedTecton singleThreadedTecton) {}

    /**
     * Törzs nélküli tecton meglátogatása.
     *
     * @param stemlessTecton a meglátogatott objektum
     */
    default void visit(StemlessTecton stemlessTecton) {}

    /**
     * Általános tecton meglátogatása.
     *
     * @param tecton a meglátogatott objektum
     */
    default void visit(Tecton tecton) {}

    /**
     * Szálakat fogyasztó tecton meglátogatása.
     *
     * @param threadConsumingTecton a meglátogatott objektum
     */
    default void visit(ThreadConsumingTecton threadConsumingTecton) {}

    /**
     * Sebességnövelő spóra meglátogatása.
     *
     * @param spore a meglátogatott spóra
     */
    default void visit(SpeedingSpore spore) {}

    /**
     * Lassító spóra meglátogatása.
     *
     * @param spore a meglátogatott spóra
     */
    default void visit(SlowingSpore spore) {}

    /**
     * Bénító spóra meglátogatása.
     *
     * @param spore a meglátogatott spóra
     */
    default void visit(ParalyzingSpore spore) {}

    /**
     * Karmos bénító spóra meglátogatása.
     *
     * @param spore a meglátogatott spóra
     */
    default void visit(ClawParalyzingSpore spore) {}

    /**
     * Osztódó spóra meglátogatása.
     *
     * @param spore a meglátogatott spóra
     */
    default void visit(SplitterSpore spore) {}

    /**
     * Rovar meglátogatása.
     *
     * @param insect a meglátogatott rovar
     */
    default void visit(Insect insect) {}

    /**
     * Gombaszál meglátogatása.
     *
     * @param thread a meglátogatott szál
     */
    default void visit(MushroomThread thread) {}

    /**
     * Gombatörzs meglátogatása.
     *
     * @param stem a meglátogatott törzs
     */
    default void visit(MushroomStem stem) {}
}
