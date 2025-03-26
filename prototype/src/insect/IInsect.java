package insect;

import java.util.List;

public interface IInsect {
    public boolean add(Insect insect);

    public boolean remove(Insect insect);

    public List<Insect> getInsects();
}