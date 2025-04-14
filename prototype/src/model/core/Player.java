package model.core;

public abstract class Player extends Identifiable implements ITurn {
    private Integer score;

    /**
     * Visszaadja a játékos pontszámát
     *
     * @return A játékos pontszáma
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Hozzáad valamennyit a játékos pontszámához
     *
     * @param score Amennyit hozzá ad
     */
    public void addScore(Integer score) {
        this.score += score;
    }
}