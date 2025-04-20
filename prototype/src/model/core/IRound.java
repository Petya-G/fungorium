package model.core;

/**
 * Interfész, olyan osztályok implementálják, akik minden kör után csinálnak valamit.
 */
public interface IRound {
    /**
     * Minden kör után végrehajtódik
     */
    public abstract void endRound();
}