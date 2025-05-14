package model.tecton;

import controller.visitor.GameObjectVisitor;
import model.Game;
import model.core.GameObject;
import model.core.IRound;
import model.core.Player;
import model.insect.IInsect;
import model.insect.Insect;
import model.mushroom.*;
import model.mushroom.spore.ISpore;
import model.mushroom.spore.Spore;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Tekton osztály, tárolja a hozzá tartozó gombatest, fonalakat, spórákat, rovarokat és szomszédos tektonokat.
 */
public class Tecton extends GameObject implements IRound, ISpore, IStem, IThread, IInsect {
    /**
     * Tektonon lévő gombatest.
     */
    protected MushroomStem stem;
    /**
     * Tektonon lévő spórák.
     */
    protected List<Spore> spores = new ArrayList<>();
    /**
     * Tektonon lévő gombafonalak.
     */
    protected List<MushroomThread> threads = new ArrayList<>();
    /**
     * Tektonon lévő rovarok.
     */
    protected List<Insect> insects = new ArrayList<>();
    /**
     * Tektonon szomszédjai.
     */
    protected List<Tecton> neighbours = new ArrayList<>();

    /**
     * Tekton pozíciója a pályán
     */
    protected double posX = 0;
    protected double posY = 0;

    /**
     * A Tecton osztály konstruktora, üresen inicializálja a lista típusú
     * tagváltozókat
     */
    public Tecton() {
        super();

    }

    public Tecton(Tecton tecton) {
        super(tecton);
        this.stem = tecton.stem;
        this.spores.addAll(tecton.spores);
        this.threads.addAll(tecton.threads);
        this.insects.addAll(tecton.insects);
        this.neighbours.addAll(tecton.neighbours);
    }

    /**
     * Tekton pozíciója getter/setter
     */
    public double getPosX() {
        return posX;
    }

    public void setPosX(double p) {
        posX = p;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double p) {
        posY = p;
    }

    /**
     * Kiszámolja a legrövidebb távolságot (lépésszámot) a jelenlegi tekton és a megadott tekton között.
     * Nem a fonalakon keresztül számoljuk, csak szomszédságra alapul.
     *
     * @param destination A cél tekton.
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

            for (final Tecton neighbor : current.neighbours) {
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

        int rnd = Game.random.nextInt(5);
        if (rnd == 0) t = new SingleThreadedTecton();
        else if (rnd == 1) t = new StemlessTecton();
        else if (rnd == 2) t = new ThreadConsumingTecton();
        else if (rnd == 3) t = new LifeSupportTecton();

        for (Tecton n : neighbours)
            if (Game.random.nextBoolean()) t.addNeighbour(n);

        threads.clear();

        List<Spore> sporesToMove = spores.stream().filter(sp -> Game.random.nextBoolean()).toList();

        for (Spore sp : sporesToMove)
            sp.setLocation(t);

        List<Insect> insectsToMove = insects.stream().filter(i -> Game.random.nextBoolean()).toList();

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
        List<Tecton> connectedNeighbours = new ArrayList<>();
        for (Tecton neighbour : getNeighbours()) {
            for (MushroomThread thread : neighbour.getThreads()) {
                if (thread.getOwner().equals(owner)) {
                    connectedNeighbours.add(neighbour);
                    break;
                }
            }
        }
        return connectedNeighbours;
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

    /**
     * Visszaadja a tektonhoz tartozó gombatestek listáját.
     * Ha nincs gombatest, üres listát ad vissza.
     *
     * @return A tektonhoz tartozó gombatestek listája
     */
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
     * Minden kör végén lefut. Alapértelmezetten nem csinál semmit, szükség esetén felüldefiniálható.
     */
    @Override
    public void endRound() {
    }

    /**
     * Hozzáad egy rovart a tektonhoz.
     *
     * @param insect A hozzáadni kívánt rovar
     * @return Az add művelet sikeressége (bool)
     */
    @Override
    public boolean add(Insect insect) {
        return insects.add(insect);
    }

    /**
     * Eltávolít egy rovart a tektonról.
     *
     * @param insect Az eltávolítani kívánt rovar
     * @return Az eltávolítás sikeressége (bool)
     */
    @Override
    public boolean remove(Insect insect) {
        return insects.remove(insect);
    }

    /**
     * Visszaadja a tektonon lévő rovarok listáját.
     *
     * @return Rovarok listája
     */
    @Override
    public List<Insect> getInsects() {
        return insects;
    }

    /**
     * Eltávolítja azokat a fonalakat, amelyek már nem kapcsolódnak máshová.
     */

    public void removeUnconnectedThreads() {
        List<MushroomThread> threadsToRemove = new ArrayList<>();
        for (MushroomThread th : threads) {
            if (!th.isConnected()) {
                threadsToRemove.add(th);
            }
        }

        threadsToRemove.forEach(MushroomThread::remove);
    }


    public void accept(GameObjectVisitor gameObjectVisitor) {
        gameObjectVisitor.visit(this);
    }

    /**
     * Két Tecton egyenlőségének meghatározása.
     * A stem, spores, threads, insects, neighbours és az ősök alapján hasonlítja össze az objektumokat.
     *
     * @param o Az összehasonlítandó objektum
     * @return Igaz, ha megegyeznek, egyébként hamis.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tecton tecton = (Tecton) o;
        return Objects.equals(stem, tecton.stem) && Objects.equals(spores, tecton.spores) && Objects.equals(threads, tecton.threads) && Objects.equals(insects, tecton.insects) && Objects.equals(neighbours, tecton.neighbours);
    }


    /**
     * Visszaadja a Tecton objektum hash kódját.
     *
     * @return Hash kód
     */

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stem, spores, threads, insects);
    }

    @Override
    public String toString() {
        return super.toString() + " " + "stem=" + (stem != null ? stem.getName() : "null") + ", " + "spores=[" + spores.stream().map(Spore::getName).collect(Collectors.joining(", ")) + "], " + "threads=[" + threads.stream().map(MushroomThread::getName).collect(Collectors.joining(", ")) + "], " + "insects=[" + insects.stream().map(Insect::getName).collect(Collectors.joining(", ")) + "], " + "neighbours=[" + neighbours.stream().map(Tecton::getName).collect(Collectors.joining(", ")) + "]";
    }
}