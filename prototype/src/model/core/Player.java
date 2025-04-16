package model.core;

import java.util.Objects;

public abstract class Player extends Identifiable implements ITurn {
    private Integer score = 0;

    protected Player() {
    }

    protected Player(int id) {
        super(id);
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return Objects.equals(score, player.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), score);
    }
}