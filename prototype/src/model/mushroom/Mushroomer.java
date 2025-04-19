package model.mushroom;

import java.util.ArrayList;
import java.util.List;
import model.core.Entity;
import model.core.Player;
import model.insect.Insect;
import model.mushroom.spore.ISpore;
import model.mushroom.spore.Spore;
import model.tecton.Tecton;

/**
 * A Mushroomer osztály egy játékost reprezentál, aki gombákat kezel.
 * A gombász képes spórákat, gombatesteket és gombafonalakat kezelni.
 */
public class Mushroomer extends Player implements ISpore, IStem, IThread {

    private List<Spore> spores = new ArrayList<>();
    private List<MushroomStem> stems = new ArrayList<>();
    private List<MushroomThread> threads = new ArrayList<>();

    private int grownThreadsThisTurn = 0;
    private static final int  MAX_THREADS_PER_TURN = 2;
    /**
     * Konstruktor, amely inicializálja a gombász spóráit, gombatestjeit és
     * gombafonalait.
     *
     * @param spores  A gombász spóráinak listája.
     * @param stems   A gombász gombatestjeinek listája.
     * @param threads A gombász gombafonalainak listája.
     */
    public Mushroomer(List<Spore> spores, List<MushroomStem> stems, List<MushroomThread> threads) {
        this.spores = spores;
        this.stems = stems;
        this.threads = threads;
    }

    /**
     * Alapértelmezett konstruktor
     * Üres listákat hoz létre a spóráknak, gombatesteknek és gombafonalaknak.
     * @param location Gombász kezdeti pozícióját meghatározó tekton.
     */
    public Mushroomer(Tecton location) {
        stems.add(new MushroomStem(this, location));
        threads.add(new MushroomThread(this, location));
    }

    /**
     * Gombatestet helyez el egy adott tektonon.
     *
     * @param tecton A tekton, ahová a gombatestet elhelyezzük.
     * @return Igaz, ha a gombatest elhelyezése sikeres, egyébként hamis.
     */
    public Boolean plantMushroomstem(Tecton tecton) {
        MushroomStem ms = new MushroomStem(this, tecton);

        if (!hasThread(tecton)) return false;

        MushroomThread thread = threads.stream().filter(th -> th.hasEaten() && th.getLocation() == tecton).findFirst().get();

        if (thread != null && tecton.add(ms)) {
            thread.setEaten(false);
            add(ms);
            addScore(1);
            return true;
        }

        List<Spore> sp = getSpores(tecton);
        if (sp.size() >= ms.getCost() && tecton.add(ms)) {
            for (int i = 0; i < ms.getCost(); i++)
                sp.get(i).remove();

            add(ms);
            addScore(1);
            return true;
        }

        return false;
    }

    /**
     * Gombafonalat növeszt egy adott tektonon.
     *
     * @param tecton A tekton, ahová a gombafonalat növesztjük.
     * @return Igaz, ha a gombafonal növesztése sikeres, egyébként hamis.
     */
    public Boolean growMushroomthread(Tecton tecton) {
        if(grownThreadsThisTurn>=MAX_THREADS_PER_TURN){
            return false;
        }
        
        MushroomThread mt = new MushroomThread(this, tecton);
        if (!tecton.hasThread(this) && tecton.neighbourHasThread(this)) {
            if (tecton.add(mt) && add(mt)) {
                grownThreadsThisTurn++;
                return true;
            }
        }
        return false;
    }

    /**
     * Spórát dob egy gombatestből egy adott tektonra.
     *
     * @param ms     A gombatest, amelyből a spórát dobjuk.
     * @param tecton A tekton, ahová a spórát dobjuk.
     * @return Igaz, ha a spóra dobása sikeres, egyébként hamis.
     */
    public Boolean throwSpore(MushroomStem ms, Tecton tecton) {
        return ms.throwSpore(tecton);
    }

    public boolean eat(MushroomThread thread, Insect insect) {
        return thread.eat(insect);
    }

    /**
     * A gombatest szintet lép.
     *
     * @param ms A szintet lépő gombatest.
     * @return Igaz, ha a fejlesztés sikeres, egyébként hamis.
     */
    public Boolean levelUp(MushroomStem ms) {
        return ms.levelUp();
    }

    public List<Spore> getSpores(Tecton tecton) {
        return spores.stream().filter(spore -> spore.getLocation() == tecton).toList();
    }

    public boolean hasThread(Tecton tecton) {
        return threads.stream().filter(th -> th.getLocation() == tecton).toArray().length == 1;
    }

    /**
     * A kör végén végrehajtandó műveletek.
     * Meghívja a gombászhoz tartozó gombatestek, gombafonalak és spórák endTurn
     * metódusait.
     */
    @Override
    public void endTurn() {
        stems.forEach(MushroomStem::endTurn);
        threads.forEach(MushroomThread::endTurn);
        spores.forEach(Entity::endTurn);
    }

    /**
     * Hozzáad egy spórát a gombászhoz.
     *
     * @param sp A hozzáadandó spóra.
     * @return Igaz, ha a hozzáadás sikeres, egyébként hamis.
     */
    @Override
    public boolean add(Spore sp) {
        return spores.add(sp);
    }

    /**
     * Elvesz egy spórát a gombásztól.
     *
     * @param sp Az eltávolítandó spóra.
     * @return Igaz, ha az eltávolítás sikeres, egyébként hamis.
     */
    @Override
    public boolean remove(Spore sp) {
        return spores.remove(sp);
    }

    @Override
    public List<Spore> getSpores() {
        return spores;
    }

    /**
     * Hozzáad egy gombatestet a gombászhoz.
     *
     * @param ms A hozzáadandó gombatest.
     * @return Igaz, ha a hozzáadás sikeres, egyébként hamis.
     */
    @Override
    public boolean add(MushroomStem ms) {
        stems.add(ms);
        System.out.println(stems);
        return true;
    }

    /**
     * Elvesz egy gombatestet a gombásztól.
     *
     * @param ms Az eltávolítandó gombatest.
     * @return Igaz, ha az eltávolítás sikeres, egyébként hamis.
     */
    @Override
    public boolean remove(MushroomStem ms) {
        return stems.remove(ms);
    }

    /**
     * Visszaadja a gombászhoz tartozó gombatestek listáját.
     *
     * @return A gombászhoz tartozó gombatestek listája.
     */
    @Override
    public List<MushroomStem> getStems() {
        return stems;
    }

    /**
     * Hozzáad egy gombafonalat a gombászhoz.
     *
     * @param th A hozzáadandó gombafonal.
     * @return Igaz, ha a hozzáadás sikeres, egyébként hamis.
     */
    @Override
    public boolean add(MushroomThread th) {
        return threads.add(th);
    }

    /**
     * Elvesz egy gombafonalat a gombásztól.
     *
     * @param th Az eltávolítandó gombafonal.
     * @return Igaz, ha az eltávolítás sikeres, egyébként hamis.
     */
    @Override
    public boolean remove(MushroomThread th) {
        return threads.remove(th);
    }

    /**
     * Visszaadja a gombászhoz tartozó gombafonalak listáját.
     *
     * @return A gombászhoz tartozó gombafonalak listája.
     */
    @Override
    public List<MushroomThread> getThreads() {
        return threads;
    }
}