package controller;

import model.core.GameObject;
import model.insect.Insect;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.spore.ClawParalyzingSpore;
import model.mushroom.spore.ParalyzingSpore;
import model.mushroom.spore.SlowingSpore;
import model.mushroom.spore.SpeedingSpore;
import model.mushroom.spore.SplitterSpore;
import model.tecton.*;

public interface GameObjectVisitor {
    void visit(GameObject gameObject);
    void visit(LifeSupportTecton lifeSupportTecton);
    void visit(SingleThreadedTecton singleThreadedTecton);
    void visit(StemlessTecton stemlessTecton);
    void visit(Tecton tecton);
    void visit(ThreadConsumingTecton threadConsumingTecton);

    void visit(SpeedingSpore spore);
    void visit(SlowingSpore spore);
    void visit(ParalyzingSpore spore);
    void visit(ClawParalyzingSpore spore);
    void visit(SplitterSpore spore);

    void visit(Insect insect);

    void visit(MushroomThread thread);
    void visit(MushroomStem stem);
}
