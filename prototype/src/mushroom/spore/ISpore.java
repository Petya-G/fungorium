package mushroom.spore;

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

    List<Spore> getSpores();
}