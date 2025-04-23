package model.core;

import java.util.Objects;

/**
 * Absztrakt osztály, amely egy játékost definiál
 */
public abstract class Player extends Identifiable implements ITurn {
    /**
     * Játékos pontszáma
     */
    private Integer score = 0;

    /**
     * Létrehoz egy játékost.
     */
    protected Player() {
    }

    /**
     * Létrehoz egy játékost a megadott azonosítóval.
     *
     * @param id Az egyedi azonosító
     */
    protected Player(int id) {
        super(id);
    }

    protected Player(Player player) {
        super(player);
        this.score = player.score;
    }

    /**
     * Visszaadja a játékos aktuális pontszámát.
     * @return Jelenlegi pontszám
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Megnöveli a játékos pontszámát a megadott értékkel.
     * @param score Hozzáadandó pontszám
     */
    public void addScore(Integer score) {
        this.score += score;
    }

    /**
     * Ellenőrzi, hogy két játékos objektum azonos-e, az azonosító és a pontszám alapján.
     * @param o Az összehasonlítandó objektum
     * @return true, ha a két játékos azonos, különben false
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return Objects.equals(score, player.score);
    }

    /**
     * Visszaadja a játékos hash-kódját az azonosító és pontszám alapján.
     * @return Hash-kód érték
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), score);
    }
}