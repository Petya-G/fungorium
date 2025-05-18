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

/**
 * A látogató (Visitor) minta implementációja, amely új játékelemek
 * létrehozásáért és grafikus felületre történő hozzáadásáért felelős.
 *
 * Az osztály minden meglátogatott objektumhoz a megfelelő ikonokkal
 * ellátott gombokat hoz létre és elhelyezi azokat a játék felületén.
 */
public class NewObjectVisitor implements GameObjectVisitor {

    /**
     * Életfenntartó tecton megjelenítése a térképen.
     */
    @Override
    public void visit(LifeSupportTecton lifeSupportTecton) {
        Controller.getMapPanel().addTectonButton(
                new TectonButton(lifeSupportTecton, ImageManager.getIcon("tecton_lifesupport"), Controller.getMapPanel()));
    }

    /**
     * Egy szálon futó tecton megjelenítése a térképen.
     */
    @Override
    public void visit(SingleThreadedTecton singleThreadedTecton) {
        Controller.getMapPanel().addTectonButton(
                new TectonButton(singleThreadedTecton, ImageManager.getIcon("tecton_singlethreaded"), Controller.getMapPanel()));
    }

    /**
     * Törzs nélküli tecton megjelenítése a térképen.
     */
    @Override
    public void visit(StemlessTecton stemlessTecton) {
        Controller.getMapPanel().addTectonButton(
                new TectonButton(stemlessTecton, ImageManager.getIcon("tecton_stemless"), Controller.getMapPanel()));
    }

    /**
     * Általános tecton megjelenítése a térképen.
     */
    @Override
    public void visit(Tecton tecton) {
        Controller.getMapPanel().addTectonButton(
                new TectonButton(tecton, ImageManager.getIcon("tecton_basic"), Controller.getMapPanel()));
    }

    /**
     * Szálakat fogyasztó tecton megjelenítése a térképen.
     */
    @Override
    public void visit(ThreadConsumingTecton threadConsumingTecton) {
        Controller.getMapPanel().addTectonButton(
                new TectonButton(threadConsumingTecton, ImageManager.getIcon("tecton_threadconsuming"), Controller.getMapPanel()));
    }

    /**
     * Sebességnövelő spóra megjelenítése a tecton tartalom spóra paneljén.
     */
    @Override
    public void visit(SpeedingSpore spore) {
        int playerId = ((Mushroomer) spore.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("spore_speeding_" + ((playerId) % 4 + 1));
        GameButton button = new SporeButton(spore, ii, Controller.getTectonContent().getSporesPanel());
        Controller.getTectonContent().getSporesPanel().addComponent(button);
    }

    /**
     * Lassító spóra megjelenítése a tecton tartalom spóra paneljén.
     */
    @Override
    public void visit(SlowingSpore spore) {
        int playerId = ((Mushroomer) spore.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("spore_slowing_" + ((playerId) % 4 + 1));
        GameButton button = new SporeButton(spore, ii, Controller.getTectonContent().getSporesPanel());
        Controller.getTectonContent().getSporesPanel().addComponent(button);
    }

    /**
     * Bénító spóra megjelenítése a tecton tartalom spóra paneljén.
     */
    @Override
    public void visit(ParalyzingSpore spore) {
        int playerId = ((Mushroomer) spore.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("spore_paralyzing_" + ((playerId) % 4 + 1));
        GameButton button = new SporeButton(spore, ii, Controller.getTectonContent().getSporesPanel());
        Controller.getTectonContent().getSporesPanel().addComponent(button);
    }

    /**
     * Karmos bénító spóra megjelenítése a tecton tartalom spóra paneljén.
     */
    @Override
    public void visit(ClawParalyzingSpore spore) {
        int playerId = ((Mushroomer) spore.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("spore_clawparalyzing_" + ((playerId) % 4 + 1));
        GameButton button = new SporeButton(spore, ii, Controller.getTectonContent().getSporesPanel());
        Controller.getTectonContent().getSporesPanel().addComponent(button);
    }

    /**
     * Osztódó spóra megjelenítése a tecton tartalom spóra paneljén.
     */
    @Override
    public void visit(SplitterSpore spore) {
        int playerId = ((Mushroomer) spore.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("spore_splitter_" + ((playerId) % 4 + 1));
        GameButton button = new SporeButton(spore, ii, Controller.getTectonContent().getSporesPanel());
        Controller.getTectonContent().getSporesPanel().addComponent(button);
    }

    /**
     * Gombaszál (thread) megjelenítése a tecton tartalom szál paneljén.
     */
    @Override
    public void visit(MushroomThread thread) {
        int playerId = ((Mushroomer) thread.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("mushroom_thread_" + ((playerId) % 4 + 1));
        GameButton button = new MushroomThreadButton(thread, ii, Controller.getTectonContent().getThreadsPanel());
        Controller.getTectonContent().getThreadsPanel().addComponent(button);
    }

    /**
     * Gombatörzs (stem) megjelenítése a tecton tartalom törzs paneljén.
     */
    @Override
    public void visit(MushroomStem stem) {
        int playerId = ((Mushroomer) stem.getOwner()).getShroomerID();
        ImageIcon ii = ImageManager.getIcon("mushroom_stem_" + ((playerId) % 4 + 1));
        GameButton button = new MushroomStemButton(stem, ii, Controller.getTectonContent().getStemsPanel());
        Controller.getTectonContent().getStemsPanel().addComponent(button);
    }

    /**
     * Rovar megjelenítése a tecton tartalom rovar paneljén, a bénultsági állapot alapján különböző ikonnal.
     */
    @Override
    public void visit(Insect insect) {
        int playerId = ((Insecter) insect.getOwner()).getInsecterID();
        String p = insect.isParalyzed() ? "paralyzed_" : "normal_";
        ImageIcon ii = ImageManager.getIcon("insect_" + p + ((playerId + 1) % 4 + 1));
        GameButton button = new InsectButton(insect, ii, Controller.getTectonContent().getStemsPanel());
        Controller.getTectonContent().getInsectPanel().addComponent(button);
    }
}
