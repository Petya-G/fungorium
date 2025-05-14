package controller.visitor;

import controller.Controller;
import model.insect.Insect;
import model.insect.Insecter;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.mushroom.spore.*;
import model.tecton.*;
import view.ImageManager;
import view.game.buttons.*;

import javax.swing.*;


public class NewObjectVisitor implements GameObjectVisitor {

    @Override
    public void visit(LifeSupportTecton lifeSupportTecton) {
        Controller.getMapPanel().addTectonButton(new TectonButton(lifeSupportTecton, ImageManager.getIcon("tecton_lifesupport"), Controller.getMapPanel()));
    }

    @Override
    public void visit(SingleThreadedTecton singleThreadedTecton) {
        Controller.getMapPanel().addTectonButton(new TectonButton(singleThreadedTecton, ImageManager.getIcon("tecton_singlethreaded"), Controller.getMapPanel()));
    }

    @Override
    public void visit(StemlessTecton stemlessTecton) {
        Controller.getMapPanel().addTectonButton(new TectonButton(stemlessTecton, ImageManager.getIcon("tecton_stemless"), Controller.getMapPanel()));
    }

    @Override
    public void visit(Tecton tecton) {
        Controller.getMapPanel().addTectonButton(new TectonButton(tecton, ImageManager.getIcon("tecton_basic"), Controller.getMapPanel()));
    }

    @Override
    public void visit(ThreadConsumingTecton threadConsumingTecton) {
        Controller.getMapPanel().addTectonButton(new TectonButton(threadConsumingTecton, ImageManager.getIcon("tecton_threadconsuming"), Controller.getMapPanel()));
    }

    //spore
    @Override
    public void visit(SpeedingSpore spore) {
        //TODO
        //if (spore == null || spore.getOwner() == null) throw new Exception();

        int playerId = ((Mushroomer) spore.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("spore_speeding_" + ((playerId) % 4 + 1));

        JButton button = new SporeButton(spore, ii, Controller.getMapPanel().getTectonContentPanel());
        Controller.getMapPanel().getTectonContentPanel().addSporeComponent(button);
    }

    @Override
    public void visit(SlowingSpore spore) {
        int playerId = ((Mushroomer) spore.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("spore_slowing_" + ((playerId) % 4 + 1));

        JButton button = new SporeButton(spore, ii, Controller.getMapPanel().getTectonContentPanel());
        Controller.getMapPanel().getTectonContentPanel().addSporeComponent(button);
    }

    @Override
    public void visit(ParalyzingSpore spore) {
        int playerId = ((Mushroomer) spore.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("spore_paralyzing_" + ((playerId) % 4 + 1));

        JButton button = new SporeButton(spore, ii, Controller.getMapPanel().getTectonContentPanel());
        Controller.getMapPanel().getTectonContentPanel().addSporeComponent(button);
    }

    @Override
    public void visit(ClawParalyzingSpore spore) {
        int playerId = ((Mushroomer) spore.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("spore_clawparalyzing_" + ((playerId) % 4 + 1));

        JButton button = new SporeButton(spore, ii, Controller.getMapPanel().getTectonContentPanel());
        Controller.getMapPanel().getTectonContentPanel().addSporeComponent(button);
    }

    @Override
    public void visit(SplitterSpore spore) {
        int playerId = ((Mushroomer) spore.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("spore_splitter_" + ((playerId) % 4 + 1));

        JButton button = new SporeButton(spore, ii, Controller.getMapPanel().getTectonContentPanel());
        Controller.getMapPanel().getTectonContentPanel().addSporeComponent(button);
    }


    @Override
    public void visit(MushroomThread thread) {
        int playerId = ((Mushroomer) thread.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("mushroom_thread_" + ((playerId) % 4 + 1));

        JButton button = new MushroomThreadButton(thread, ii, Controller.getMapPanel().getTectonContentPanel());
        Controller.getMapPanel().getTectonContentPanel().addThreadComponent(button);
    }

    @Override
    public void visit(MushroomStem stem) {
        int playerId = ((Mushroomer) stem.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("mushroom_stem_" + ((playerId) % 4 + 1));

        JButton button = new MushroomStemButton(stem, ii, Controller.getMapPanel().getTectonContentPanel());
        Controller.getMapPanel().getTectonContentPanel().addStemComponent(button);
    }

    @Override
    public void visit(Insect insect) {
        int playerId = ((Insecter) insect.getOwner()).getInsecterID();
        String p = insect.isParalyzed() ? "paralyzed_" : "normal_";

        ImageIcon ii = ImageManager.getIcon("insect_" + p + ((playerId + 1) % 4 + 1));
        JButton button = new InsectButton(insect, ii, Controller.getMapPanel().getTectonContentPanel());
        Controller.getMapPanel().getTectonContentPanel().addInsectComponent(button);
    }
}
