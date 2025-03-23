package core;

public abstract class Player implements ITurn {
    protected Integer score;

    /**
     * Visszaadja a játékos pontszámát
     * @return A játékos pontszáma
     */
    public abstract Integer getScore();

    /**
     * Hozzáad valamennyit a játékos pontszámához
     * @param score Amennyit hozzá ad
     */
    public abstract void addScore(Integer score);

    @Override
    public abstract void endTurn();
}