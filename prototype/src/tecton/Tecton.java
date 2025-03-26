package tecton;

import core.*;
import insect.*;

import java.util.*;

import mushroom.*;
import mushroom.spore.*;

public class Tecton implements IRound, ISpore, IStem, IThread, IInsect {
    protected MushroomStem stem;
    protected List<Spore> spores = new ArrayList<Spore>();
    protected List<MushroomThread> threads = new ArrayList<MushroomThread>();
    protected List<Insect> insects = new ArrayList<Insect>();
    protected List<Tecton> neighbours = new ArrayList<Tecton>();

    /**
     * A Tecton osztály konstruktora, üresen inicializálja a lista típusú
     * tagváltozókat
     */
    public Tecton() {
        Debug.DBGFUNC("Tecton lerakva");
    }

    public Tecton(List<Spore> spores, List<MushroomThread> threads, List<Insect> insects, List<Tecton> neighbours) {
        this.spores = spores;
        this.threads = threads;
        this.insects = insects;
        this.neighbours = neighbours;
    }

    /**
     * visszaadja, hogy egy adott játékosnak mennyi spórája van a tektonon
     * 
     * @param p A játékos, akinek a spóráit számoljuk
     * @return A játékos spóráinak száma a tektonon
     */
    public Integer getSporeCount(Player p) {
        return spores.stream().filter(sp -> sp.getOwner() == p).toArray().length;
    }

    /**
     * Széttöri a tekton kettő darabra úgy, hogy megtartja az eredeti tekton,
     * létrehoz egy újat, beállítja a szomszédságokat és visszaadja az új tektont
     * 
     * @return Az új tekton
     */
    public Tecton split() {
        Debug.DBGFUNC("Tecton szétválik");
        Tecton t = new Tecton();

        int rnd = new Random().nextInt(4);
        if (rnd == 0)
            t = new SingleThreadedTecton();
        else if (rnd == 1)
            t = new StemlessTecton();
        else if (rnd == 2)
            t = new ThreadConsumingTecton();

        for (Tecton n : neighbours) {
            if (new Random().nextBoolean()) {
                t.addNeighbour(n);
            }
        }

        threads.clear();

        List<Spore> sporesToMove = spores.stream()
                .filter(sp -> new Random().nextBoolean())
                .toList();

        for (Spore sp : sporesToMove)
            sp.setLocation(t);

        List<Insect> insectsToMove = insects.stream()
                .filter(i -> new Random().nextBoolean())
                .toList();

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
        return stem == null ? new ArrayList<MushroomStem>() : List.of(stem);
    }

    /**
     * Visszaadja a tektonon lévő spórák listáját, amelyek az adott játékoshoz
     * tartoznak
     * 
     * @param owner A játékos, akinek a spóráit keressük
     * @return A játékos spórái, amik a tektonon vannak
     */
    public List<Spore> getSpores(Player owner) {
        return spores.stream().filter(sp -> sp.getOwner() == owner).toList();
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
        return threads.stream().filter(m -> m.getOwner() == p).toArray().length == 1;
    }

    /**
     * Visszadja, hogy az adott játékosnak van-e fonala a tekton bármely szomszédján
     * 
     * @param p A játékos, akinek a fonalait vizsgáljuk
     * @return van-e a játékosnak fonala szomszédon? (bool)
     */
    public boolean neighbourHasThread(Player p) {
        for (Tecton t : neighbours)
            if (t.hasThread(p))
                return true;

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
        if (hasStem())
            return false;

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
        threads.stream()
                .filter(t -> !t.isConnected())
                .toList().forEach(th -> th.remove());
    }
}