package model.tecton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import model.core.GlobalRandom;
import model.core.IRound;
import model.core.Identifiable;
import model.core.Player;
import model.insect.IInsect;
import model.insect.Insect;
import model.mushroom.IStem;
import model.mushroom.IThread;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.mushroom.spore.ISpore;
import model.mushroom.spore.Spore;

public class Tecton extends Identifiable implements IRound, ISpore, IStem, IThread, IInsect {
    protected MushroomStem stem;
    protected List<Spore> spores = new ArrayList<>();
    protected List<MushroomThread> threads = new ArrayList<>();
    protected List<Insect> insects = new ArrayList<>();
    protected List<Tecton> neighbours = new ArrayList<>();

    /**
     * A Tecton osztály konstruktora, üresen inicializálja a lista típusú
     * tagváltozókat
     */
    public Tecton() {
    }

    public Tecton(MushroomStem stem, List<Spore> spores, List<MushroomThread> threads, List<Insect> insects, List<Tecton> neighbours) {
        this.stem = stem;
        this.spores = spores;
        this.threads = threads;
        this.insects = insects;
        this.neighbours = neighbours;
    }

    /**
     * Kiszámolja a legrövidebb távolságot (lépésszámot) a jelenlegi tekton és a megadott tekton között.
     * Nem a fonalakon keresztül számoljuk, csak szomszédságra alapul.
     *
     * @param other A cél tekton.
     * @return A minimális lépésszám, vagy Integer.MAX_VALUE, ha nem elérhető.
     */
    public int distanceTo(Tecton destination) {
        if (this == destination) return 0;

        Set<Tecton> visited = new HashSet<>();
        Queue<Tecton> queue = new LinkedList<>();
        Queue<Integer> depths = new LinkedList<>();

        queue.add(this);
        depths.add(0);
        visited.add(this);

        while (!queue.isEmpty()) {
            Tecton current = queue.poll();
            int currentDepth = depths.poll();

            for (Tecton neighbor : current.getNeighbours()) {
                if (neighbor == destination) {
                    return currentDepth + 1;
                }

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    depths.add(currentDepth + 1);
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    /**
     * Széttöri a tekton kettő darabra úgy, hogy megtartja az eredeti tekton,
     * létrehoz egy újat, beállítja a szomszédságokat és visszaadja az új tektont
     *
     * @return Az új tekton
     */
    public Tecton split() {
        Tecton t = new Tecton();

        int rnd = GlobalRandom.getInstance().nextInt(5);
        if (rnd == 0) t = new SingleThreadedTecton();
        else if (rnd == 1) t = new StemlessTecton();
        else if (rnd == 2) t = new ThreadConsumingTecton();
        else if (rnd == 3) t = new LifeSupportTecton();

        for (Tecton n : neighbours) {
            if (GlobalRandom.getInstance().nextBoolean()) {
                t.addNeighbour(n);
            }
        }

        threads.clear();

        List<Spore> sporesToMove = spores.stream().filter(sp -> GlobalRandom.getInstance().nextBoolean()).toList();

        for (Spore sp : sporesToMove)
            sp.setLocation(t);

        List<Insect> insectsToMove = insects.stream().filter(i -> GlobalRandom.getInstance().nextBoolean()).toList();

        for (Insect i : insectsToMove)
            i.setLocation(t);

        return t;
    }

    /**
     * Visszaadja a tekton szomszédjainak listáját
     *
     * @return A tekton szomszédjainak listája
     */
    public List<Tecton> getNeighbours() {
        return neighbours;
    }

    /**
     * Visszaadja a tekton szomszédjainak listáját, amik össze vannak kötve a megadott Mushroomer fonalával
     *
     * @return Összekötött tekton szomszédjainak listája
     */
    public List<Tecton> getConnectedNeighbours(Mushroomer owner) {
        return getNeighbours().stream()
            .filter(neighbour -> neighbour.getThreads().stream()
            .anyMatch(thread ->
                thread.getOwner().equals(owner) &&
                getThreads().contains(thread)))
            .toList();
    }

    /**
     * Visszaadja a tektonon lévő fonalak listáját
     *
     * @return A tektonon lévő fonalak listája
     */
    @Override
    public List<MushroomThread> getThreads() {
        return threads;
    }

    /**
     * Visszaadja a tektonon lévő spórák listáját
     *
     * @return A tektonon lévő spórák listája
     */
    @Override
    public List<Spore> getSpores() {
        return spores;
    }

    @Override
    public List<MushroomStem> getStems() {
        return stem == null ? new ArrayList<>() : List.of(stem);
    }

    /**
     * Hozzáadja a kapott tekton a tekton szomszédjaihoz
     *
     * @param t Az új szomszéd
     */
    public void addNeighbour(Tecton t) {
        neighbours.add(t);
    }

    /**
     * Visszadja, hogy az adott játékosnak van-e fonala a tektonon
     *
     * @param p A játékos, akinek a fonalát keressük
     * @return van-e a játékosnak fonala? (bool)
     */
    public boolean hasThread(Player p) {
        return threads.stream().filter(m -> m.getOwner().equals(p)).toArray().length == 1;
    }

    /**
     * Visszadja, hogy az adott játékosnak van-e fonala a tekton bármely szomszédján
     *
     * @param p A játékos, akinek a fonalait vizsgáljuk
     * @return van-e a játékosnak fonala szomszédon? (bool)
     */
    public boolean neighbourHasThread(Player p) {
        for (Tecton t : neighbours)
            if (t.hasThread(p)) return true;

        return false;
    }

    /**
     * Visszaadja, hogy a tektonon van-e gombatest
     *
     * @return van-e gombatest a tektonon (bool)
     */
    public boolean hasStem() {
        return stem != null;
    }

    /**
     * Rárak egy gombafonalat a tektonra
     *
     * @param th A rárakandó gombafonál
     * @return Az add sikeressége (bool)
     */
    @Override
    public boolean add(MushroomThread th) {
        return threads.add(th);
    }

    /**
     * Eltávolít egy gombafonalat a tektonról
     *
     * @param th Az eltávolítandó gombafonál
     * @return Az remove sikeressége (bool)
     */
    @Override
    public boolean remove(MushroomThread th) {
        return threads.remove(th);
    }

    /**
     * Rárak egy gombatestet a tektonra
     *
     * @param ms A rárakandó gombatest
     * @return Az add sikeressége (bool)
     */
    @Override
    public boolean add(MushroomStem ms) {
        if (hasStem()) return false;

        stem = ms;
        return true;
    }

    /**
     * Eltávolít egy gombatestet a tektonról
     *
     * @param ms A eltávolítandó gombatest
     * @return A remove sikeressége (bool)
     */
    @Override
    public boolean remove(MushroomStem ms) {
        stem = null;
        return true;
    }

    /**
     * Hozzáad egy spórát a tektonon lévő spórákhoz
     *
     * @param sp A hozzáadott spóra
     * @return Az add sikeressége (bool)
     */
    @Override
    public boolean add(Spore sp) {
        return spores.add(sp);
    }

    /**
     * Eltávolít egy spórát a tektonról
     *
     * @param sp A eltávolítandó spóra
     * @return A remove sikeressége (bool)
     */
    @Override
    public boolean remove(Spore sp) {
        return spores.remove(sp);
    }

    /**
     * Minden kör végén lefut
     */
    @Override
    public void endRound() {
    }

    @Override
    public boolean add(Insect insect) {
        return insects.add(insect);
    }

    @Override
    public boolean remove(Insect insect) {
        return insects.remove(insect);
    }

    @Override
    public List<Insect> getInsects() {
        return insects;
    }

    public void removeUnconnectedThreads() {
        threads.stream().filter(t -> !t.isConnected()).toList().forEach(MushroomThread::remove);
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tecton tecton = (Tecton) o;
        return Objects.equals(stem, tecton.stem) && Objects.equals(spores, tecton.spores) && Objects.equals(threads, tecton.threads) && Objects.equals(insects, tecton.insects) && Objects.equals(neighbours, tecton.neighbours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stem, spores, threads, insects, neighbours);
    }
}