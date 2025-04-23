package model.mushroom;

import model.core.Entity;
import model.core.Player;
import model.insect.Insect;
import model.mushroom.spore.ISpore;
import model.mushroom.spore.Spore;
import model.tecton.Tecton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Mushroomer osztály egy játékost reprezentál, aki gombákat kezel.
 * A gombász képes spórákat, gombatesteket és gombafonalakat kezelni.
 */
public class Mushroomer extends Player implements ISpore, IStem, IThread {

    private final List<MushroomStem> stems = new ArrayList<>();
    private final List<MushroomThread> threads = new ArrayList<>();
    private final List<Spore> spores = new ArrayList<>();

    private static final int MAX_THREADS_PER_TURN = 2;
    private int grownThreadsThisTurn = 0;

    public Mushroomer() {
    }

    /**
     * Alapértelmezett konstruktor
     * Üres listákat hoz létre a spóráknak, gombatesteknek és gombafonalaknak.
     *
     * @param location Gombász kezdeti pozícióját meghatározó tekton.
     */
    public Mushroomer(Tecton location) {
        MushroomStem s1=new MushroomStem(this, location);
        stems.add(s1);
        location.add(s1);

        MushroomThread t1=new MushroomThread(this, location);
        threads.add(new MushroomThread(this, location));
        location.add(t1);
    }

    /**
     * Konstruktor azonosítóval.
     * Létrehoz egy új gombatestet és gombafonalat a megadott tektonra, és beállítja az azonosítót.
     * A létrehozott entitások hozzáadásra kerülnek a tektonhoz is.
     *
     * @param location A gombász kezdeti pozícióját meghatározó tekton.
     * @param id Azonosító, amely egyedileg azonosítja a gombászt.
     */
    public Mushroomer(Tecton location, int id) {
        super(id);
        MushroomStem s1=new MushroomStem(this, location);
        stems.add(s1);
        location.add(s1);

        MushroomThread t1=new MushroomThread(this, location);
        threads.add(new MushroomThread(this, location));
        location.add(t1);
    }

    /**
     * Másoló konstruktor.
     * Átmásolja a megadott gombász minden fontos állapotát (spórák, gombatestek, gombafonalak, növesztések száma).
     * Az entitások példányai nem mély másolatként jönnek létre, hanem referenciaként.
     *
     * @param mushroomer A másolandó gombász példány.
     */
    public Mushroomer(Mushroomer mushroomer) {
        super(mushroomer);
        this.spores.addAll(mushroomer.spores);
        this.stems.addAll(mushroomer.stems);
        this.threads.addAll(mushroomer.threads);
        this.grownThreadsThisTurn = mushroomer.grownThreadsThisTurn;
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
     * Megvizsgálja, hogy a gombásznak van-e gombateste a megadott tektonon.
     *
     * @param tecton A tekton, amelyen gombatestet keresünk.
     * @return {@code true}, ha van teste a tektonon, különben {@code false}.
     */
    public boolean hasStem(Tecton tecton) {
        return stems.stream().anyMatch(stem -> stem.getLocation().equals(tecton));
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

    /**
     * Megvizsgálja, hogy a gombásznak van-e gombafonala a megadott tektonon.
     *
     * @param tecton A tekton, amelyen fonalat keresünk.
     * @return {@code true}, ha van fonala a tektonon, különben {@code false}.
     */
    public boolean hasThread(Tecton tecton) {
        return threads.stream().anyMatch(th -> th.getLocation().equals(tecton));
    }

    /**
     * Visszaadja a gombász összes spóráját.
     *
     * @return A gombászhoz tartozó spórák listája.
     */
    @Override
    public List<Spore> getSpores() {
        return spores;
    }

    /**
     * Visszaadja azokat a spórákat, amelyek a megadott tektonon találhatók, és a gombásztól származnak.
     *
     * @param tecton A tekton, amelyen a keresett spórák helyezkednek el.
     * @return A tektonon található spórák listája.
     */
    public List<Spore> getSpores(Tecton tecton) {
        return spores.stream().filter(spore -> spore.getLocation().equals(tecton)).toList();
    }

    /**
     * Visszaadja, hogy egy körben legfeljebb hány gombafonál növeszthető a gombásszal.
     *
     * @return A maximálisan növeszthető gombafonalak száma körönként.
     */
    public int getMaxThreadsPerTurn() {
        return MAX_THREADS_PER_TURN;
    }


    /**
     * Hozzáad egy spórát a gombászhoz.
     *
     * @param sp A hozzáadandó spóra.
     * @return {@code true}, ha a hozzáadás sikeres, egyébként {@code false}.
     */
    @Override
    public boolean add(Spore sp) {
        return spores.add(sp);
    }

    /**
     * Elvesz egy spórát a gombásztól.
     *
     * @param sp Az eltávolítandó spóra.
     * @return {@code true}, ha az eltávolítás sikeres, egyébként {@code false}.
     */
    @Override
    public boolean remove(Spore sp) {
        return spores.remove(sp);
    }

    /**
     * Hozzáad egy gombatestet a gombászhoz.
     *
     * @param ms A hozzáadandó gombatest.
     * @return {@code true}, ha a hozzáadás sikeres, egyébként {@code false}.
     */
    @Override
    public boolean add(MushroomStem ms) {
        return stems.add(ms);
    }

    /**
     * Elvesz egy gombatestet a gombásztól.
     *
     * @param ms Az eltávolítandó gombatest.
     * @return {@code true}, ha az eltávolítás sikeres, egyébként {@code false}.
     */
    @Override
    public boolean remove(MushroomStem ms) {
        return stems.remove(ms);
    }

    /**
     * Hozzáad egy gombafonalat a gombászhoz.
     *
     * @param th A hozzáadandó gombafonal.
     * @return {@code true}, ha a hozzáadás sikeres, egyébként {@code false}.
     */
    @Override
    public boolean add(MushroomThread th) {
        return threads.add(th);
    }

    /**
     * Elvesz egy gombafonalat a gombásztól.
     *
     * @param th Az eltávolítandó gombafonal.
     * @return {@code true}, ha az eltávolítás sikeres, egyébként {@code false}.
     */
    @Override
    public boolean remove(MushroomThread th) {
        return threads.remove(th);
    }


    /**
     * Gombatestet helyez el egy adott tektonon.
     *
     * @param tecton A tekton, ahová a gombatestet elhelyezzük.
     * @return {@code true}, ha a gombatest elhelyezése sikeres, egyébként {@code false}.
     */
    public Boolean plantMushroomStem(Tecton tecton) {
        MushroomStem ms = new MushroomStem(this, tecton);

        if (!hasThread(tecton)) return false;

        MushroomThread thread = threads.stream()
                                        .filter(th -> th.hasEaten() && th.getLocation()
                                        .equals(tecton)).findFirst().orElse(null);;

        if (thread != null && tecton.add(ms)) {
            thread.setEaten(false);
            add(ms);
            addScore(1);
            return true;
        }

        List<Spore> sp = getSpores(tecton);
        if (sp.size() >= ms.getCost() && tecton.add(ms)) {
            for (int i = 0; i < ms.getCost(); i++) {
                Spore s = sp.get(i);
                s.remove();
                this.remove(s);
            }

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
     * @return {@code true}, ha a gombafonal növesztése sikeres, egyébként {@code false}.
     */
    public Boolean growMushroomThread(Tecton tecton) {
        if (grownThreadsThisTurn >= MAX_THREADS_PER_TURN) {
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
     * @return {@code true}, ha a spóra dobása sikeres, egyébként {@code false}.
     */
    public Boolean throwSpore(MushroomStem ms, Tecton tecton) {
        return ms.throwSpore(tecton);
    }

    /**
     * Megpróbálja a megadott gombafonallal megenni a megadott rovart.
     *
     * @param thread A gombafonal, amely megpróbálja megenni a rovart.
     * @param insect A cél rovar, amelyet a fonál megpróbál megenni.
     * @return {@code true}, ha a gombafonal sikeresen megette a rovart, különben {@code false}.
     */
    public boolean eat(MushroomThread thread, Insect insect) {
        return thread.eat(insect);
    }

    /**
     * A gombatest szintet lép.
     *
     * @param ms A szintet lépő gombatest.
     * @return {@code true}, ha a fejlesztés sikeres, egyébként {@code false}.
     */
    public Boolean levelUp(MushroomStem ms) {
        return ms.levelUp();
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

        grownThreadsThisTurn = 0;
    }

    /**
     * Összehasonlítja az aktuális objektumot egy másik objektummal.
     * Az egyenlőség akkor áll fenn, ha a két objektum azonos típusú, és minden releváns mezőjük megegyezik.
     *
     * @param o Az összehasonlítandó objektum.
     * @return {@code true}, ha az objektumok megegyeznek, különben {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mushroomer other = (Mushroomer) o;
        return grownThreadsThisTurn == other.grownThreadsThisTurn &&
                Objects.equals(spores, other.spores) &&
                Objects.equals(stems, other.stems) &&
                Objects.equals(threads, other.threads);
    }

    /**
     * Visszaadja az objektum hash-kódját, amely az aktuális példány fontos mezői alapján kerül kiszámításra.
     *
     * @return Az objektum hash-kódja.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), spores, stems, threads, grownThreadsThisTurn);
    }

}