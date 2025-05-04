package mushroom;

import java.util.*;

import mushroom.spore.*;
import core.Debug;
import core.Player;
import tecton.*;

/**
 * A Mushroomer osztály egy játékost reprezentál, aki gombákat kezel.
 * A gombász képes spórákat, gombatesteket és gombafonalakat kezelni.
 */
public class Mushroomer extends Player implements ISpore, IStem, IThread {
    private List<Spore> spores;
    private List<MushroomStem> stems;
    private List<MushroomThread> threads;

    /**
     * Visszaadja a gombászhoz tartozó gombatestek listáját.
     * @return A gombászhoz tartozó gombatestek listája.
     */
    public List<MushroomStem> getStems() {
        Debug.DBGFUNC("Gombászhoz tartozó gombatestek lekérése");
        return stems;
    }

    /**
     * Konstruktor, amely inicializálja a gombász spóráit, gombatestjeit és gombafonalait.
     * @param spores A gombász spóráinak listája.
     * @param stems A gombász gombatestjeinek listája.
     * @param threads A gombász gombafonalainak listája.
     */
    public Mushroomer(List<Spore> spores, List<MushroomStem> stems, List<MushroomThread> threads) {
        Debug.DBGFUNC("Mushroomer konstruktor");
        this.spores = spores;
        this.stems = stems;
        this.threads = threads;
    }

    /**
     * Alapértelmezett konstruktor
     * Üres listákat hoz létre a spóráknak, gombatesteknek és gombafonalaknak.
     */
    public Mushroomer() {
        Debug.DBGFUNC("Mushroomer alapértelmezett konstruktor");
        this.spores = new ArrayList<>();
        this.stems = new ArrayList<>();
        this.threads = new ArrayList<>();
    }

    /**
     * Visszaadja a gombászhoz tartozó gombafonalak listáját.
     * @return A gombászhoz tartozó gombafonalak listája.
     */
    public List<MushroomThread> getThreads() {
        Debug.DBGFUNC("Gombászhoz tartozó gombafonalak lekérése");
        return threads;
    }

    /**
     * Gombatestet helyez el egy adott tektonon.
     * @param tecton A tekton, ahová a gombatestet elhelyezzük.
     * @return Igaz, ha a gombatest elhelyezése sikeres, egyébként hamis.
     */
    public Boolean plantMushroomstem(Tecton tecton) {
        Debug.DBGFUNC("Gombatest növesztése");
        MushroomStem ms = new MushroomStem(this, tecton);
        return tecton.add(ms) ? add(ms) : false;
    }

    /**
     * Gombafonalat növeszt egy adott tektonon.
     * @param tecton A tekton, ahová a gombafonalat növesztjük.
     * @return Igaz, ha a gombafonal növesztése sikeres, egyébként hamis.
     */
    public Boolean growMushroomthread(Tecton tecton) {
        Debug.DBGFUNC("Gombafonal növesztése");
        MushroomThread mt = new MushroomThread(this, tecton);
        return tecton.add(mt) ? add(mt) : false;
    }

    /**
     * Spórát dob egy gombatestből egy adott tektonra.
     * @param ms A gombatest, amelyből a spórát dobjuk.
     * @param tecton A tekton, ahová a spórát dobjuk.
     * @return Igaz, ha a spóra dobása sikeres, egyébként hamis.
     */
    public Boolean throwSpore(MushroomStem ms, Tecton tecton) {
        Debug.DBGFUNC("Spóra dobása");
        return ms.throwSpore(tecton);
    }

    /**
     * A gombatest szintet lép.
     * @param ms A szintet lépő gombatest.
     * @return Igaz, ha a fejlesztés sikeres, egyébként hamis.
     */
    public Boolean levelUp(MushroomStem ms) {
        Debug.DBGFUNC("Gombatest szintlépés");
        return ms.levelUp();
    }

    /**
     * Hozzáad egy gombafonalat a gombászhoz.
     * @param th A hozzáadandó gombafonal.
     * @return Igaz, ha a hozzáadás sikeres, egyébként hamis.
     */
    @Override
    public boolean add(MushroomThread th) {
        Debug.DBGFUNC("Gombafonal hozzáadása");
        return threads.add(th);
    }

    /**
     * Elvesz egy gombafonalat a gombásztól.
     * @param th Az eltávolítandó gombafonal.
     * @return Igaz, ha az eltávolítás sikeres, egyébként hamis.
     */
    @Override
    public boolean remove(MushroomThread th) {
        Debug.DBGFUNC("Gombafonal eltávolítása");
        return threads.remove(th);
    }

    /**
     * Hozzáad egy gombatestet a gombászhoz.
     * @param ms A hozzáadandó gombatest.
     * @return Igaz, ha a hozzáadás sikeres, egyébként hamis.
     */
    @Override
    public boolean add(MushroomStem ms) {
        Debug.DBGFUNC("Gombatest hozzáadása");
        stems.add(ms);
        System.out.println(stems);
        return true;
    }

    /**
     * Elvesz egy gombatestet a gombásztól.
     * @param ms Az eltávolítandó gombatest.
     * @return Igaz, ha az eltávolítás sikeres, egyébként hamis.
     */
    @Override
    public boolean remove(MushroomStem ms) {
        Debug.DBGFUNC("Gombatest eltávolítása");
        return stems.remove(ms);
    }

    /**
     * Hozzáad egy spórát a gombászhoz.
     * @param sp A hozzáadandó spóra.
     * @return Igaz, ha a hozzáadás sikeres, egyébként hamis.
     */
    @Override
    public boolean add(Spore sp) {
        Debug.DBGFUNC("Spóra hozzáadása");
        return spores.add(sp);
    }

    /**
     * Elvesz egy spórát a gombásztól.
     * @param sp Az eltávolítandó spóra.
     * @return Igaz, ha az eltávolítás sikeres, egyébként hamis.
     */
    @Override
    public boolean remove(Spore sp) {
        Debug.DBGFUNC("Spóra eltávolítása");
        return spores.remove(sp);
    }

    /**
     * Visszaadja a gombász aktuális pontszámát.
     * @return A gombász pontszáma.
     */
    @Override
    public Integer getScore() {
        Debug.DBGFUNC("Gombász pontszáma lekérése");
        return score;
    }

    /**
     * Növeli a gombász pontszámát.
     * @param score A hozzáadandó pontok száma.
     */
    @Override
    public void addScore(Integer score) {
        Debug.DBGFUNC("Pont megnövelve");
        this.score += score;
    }

    /**
     * A kör végén végrehajtandó műveletek.
     * Meghívja a gombászhoz tartozó gombatestek, gombafonalak és spórák endTurn metódusait.
     */
    @Override
    public void endTurn() {
        Debug.DBGFUNC("Kör vége");
        stems.forEach(ms->ms.endTurn());
        threads.forEach(th->th.endTurn());
        spores.forEach(sp->sp.endTurn());
    }
}