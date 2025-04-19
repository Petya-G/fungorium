package model.mushroom.spore;

import java.util.List;

public interface ISpore {

    /**
     * Hozzáad valamihez egy spórát
     *
     * @param sp A hozzáadandó spóra
     * @return A művelet sikeressége
     */
    boolean add(Spore sp);

    /**
     * Eltávolít valahonnan egy spórát
     *
     * @param sp A eltávolítandó spóra
     * @return A művelet sikeressége
     */
    boolean remove(Spore sp);

    /**
     * A spórákat adja vissza
     *
     * @return Spórák listája
     */
    List<Spore> getSpores();

    /**
     * Egy megadott spórát ad vissza
     *
     * @param id Adott spóra azonosítója
     * @return Keresett spóra
     */
    default Spore getSpore(int id) {
        return getSpores().stream()
                .filter(spore -> spore.getId() == id)
                .findFirst()
                .orElse(null);
    }
}