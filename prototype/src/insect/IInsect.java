package insect;

import java.util.List;

public interface IInsect {
    boolean add(Insect insect);

    boolean remove(Insect insect);

    List<Insect> getInsects();

    default Insect getInsect(int id) {
        return getInsects().stream()
                .filter(insect -> insect.getId() == id)
                .findFirst()
                .orElse(null);
    }
}