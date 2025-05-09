package controller;

import model.core.GameObject;
import model.tecton.*;

public interface GameObjectVisitor {
    void visit(GameObject gameObject);
    void visit(LifeSupportTecton lifeSupportTecton);
    void visit(SingleThreadedTecton singleThreadedTecton);
    void visit(StemlessTecton stemlessTecton);
    void visit(Tecton tecton);
    void visit(ThreadConsumingTecton threadConsumingTecton);
}
