package controller;

import model.insect.Insect;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.spore.*;
import model.tecton.*;

public interface GameObjectVisitor {
    void visit(Insect insect);

    void visit(LifeSupportTecton lifeSupportTecton);
    void visit(SingleThreadedTecton singleThreadedTecton);
    void visit(StemlessTecton stemlessTecton);
    void visit(Tecton tecton);
    void visit(ThreadConsumingTecton threadConsumingTecton);

    void visit(MushroomThread mushroomThread);
    void visit(MushroomStem mushroomStem);

    void visit(ClawParalyzingSpore clawParalyzingSpore);
    void visit(ParalyzingSpore paralyzingSpore);
    void visit(SlowingSpore slowingSpore);
    void visit(SpeedingSpore speedingSpore);
    void visit(SplitterSpore splitterSpore);
}
