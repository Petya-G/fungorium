package model.core;

/**
 * Intefész, olyan osztályok implementálják, amik egy lépés bejezésekor csinálnak valamit.
 */
public interface ITurn {
    /**
     * Minden lépés végén végrehajtódik
     */
    void endTurn();
}