package controller.visitor;

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
    default void visit(GameObject gameObject){}
    default void visit(LifeSupportTecton lifeSupportTecton){}
    default void visit(SingleThreadedTecton singleThreadedTecton){}
    default void visit(StemlessTecton stemlessTecton){}
    default void visit(Tecton tecton){}
    default void visit(ThreadConsumingTecton threadConsumingTecton){}

    default void visit(SpeedingSpore spore){}
    default void visit(SlowingSpore spore){}
    default void visit(ParalyzingSpore spore){}
    default void visit(ClawParalyzingSpore spore){}
    default void visit(SplitterSpore spore){}

    default void visit(Insect insect){}

    default void visit(MushroomThread thread){}
    default void visit(MushroomStem stem){}
}
