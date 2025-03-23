package tecton;

import core.*;
import insect.*;

import java.util.*;

import mushroom.*;
import mushroom.spore.*;

public class Tecton implements IRound, ISpore, IStem, IThread {
    protected MushroomStem stem;
    protected List<Spore> spores;
    protected List<MushroomThread> threads;
    protected List<Tecton> neighbours;
    protected List<Insect> insects;


    /**
     * A Tecton osztály konstruktora, üresen inicializálja a lista típusú tagváltozókat
     */
    public Tecton() {
        neighbours = new ArrayList<>();
        threads = new ArrayList<>();
        spores = new ArrayList<>();
        insects = new ArrayList<>();
        
        Debug.DBGFUNC("Tecton lerakva");
    }

    /**
     * visszaadja, hogy egy adott játékosnak mennyi spórája van a tektonon
     * @param p A játékos, akinek a spóráit számoljuk
     * @return A játékos spóráinak száma a tektonon
     */
    public Integer getSporeCount(Player p) {
        return spores.stream().filter(sp -> sp.getOwner() == p).toArray().length;
    }

    /**
     * Széttöri a tekton kettő darabra úgy, hogy megtartja az eredeti tekton, létrehoz egy újat, beállítja a szomszédságokat és visszaadja az új tektont
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

        for (Spore sp : spores) {
            if (new Random().nextBoolean()) {
                t.add(sp);
                sp.setLocation(t);
                this.remove(sp);
            }
        }

        for (Insect i : insects) {
            if (new Random().nextBoolean()) {
                t.addInsect(i);
                i.setLocation(t);
                this.removeInsect(i);
            }
        }

        return t;
    }

    /**
     * Beállítja a stem tagváltozó értékét
     * @param st A MushroomStem, amit rárakunk a tektonra
     */
    public void setStem(MushroomStem st) {
        stem = st;
    }

    /**
     * Visszaadja a tekton szomszédjainak listáját
     * @return A tekton szomszédjainak listája
     */
    public List<Tecton> getNeighbours() {
        return neighbours;
    }

    /**
     * Visszaadja a tektonon lévő fonalak listáját
     * @return A tektonon lévő fonalak listája
     */
    public List<MushroomThread> getThreads() {
        return threads;
    }

    /**
     * Visszaadja a tektonon lévő spórák listáját
     * @return A tektonon lévő spórák listája
     */
    public List<Spore> getSpores() {
        return spores;
    }

    /**
     * Visszaadja a tektonon lévő spórák listáját, amelyek az adott játékoshoz tartoznak
     * @param owner A játékos, akinek a spóráit keressük
     * @return A játékos spórái, amik a tektonon vannak
     */
    public List<Spore> getSpores(Player owner) {
        return spores.stream().filter(sp -> sp.getOwner() == owner).toList();
    }

    /**
     * Hozzáadja a kapott tekton a tekton szomszédjaihoz
     * @param t Az új szomszéd
     */
    public void addNeighbour(Tecton t) {
        neighbours.add(t);
    }

    /**
     * Hozzáadja a kapott rovart a tektonon található rovarokhoz
     * @param i Az új rovar
     */
    public void addInsect(Insect i) {
        insects.add(i);
    }

    
    /**
     * Eltávolítja a kapott rovart a tektonról
     * @param i Az eltávolítandó rovar
     */
    public void removeInsect(Insect i) {
        insects.remove(i);
    }

    /**
     * Visszadja, hogy az adott játékosnak van-e fonala a tektonon
     * @param p A játékos, akinek a fonalát keressük
     * @return van-e a játékosnak fonala? (bool)
     */
    public boolean hasThread(Player p) {
        return threads.stream().filter(m -> m.getOwner() == p).toArray().length == 1;
    }

    /**
     * Visszadja, hogy az adott játékosnak van-e fonala a tekton bármely szomszédján
     * @param p A játékos, akinek a fonalait vizsgáljuk
     * @return van-e a játékosnak fonala szomszédon? (bool)
     */
    public boolean neighbourHasThread(Player p) {
        for (Tecton t : neighbours) {
            if (t.hasThread(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Rárak egy gombafonalat a tektonra
     * @param th A rárakandó gombafonál
     * @return Az add sikeressége (bool)
     */
    @Override
    public boolean add(MushroomThread th) {
        Debug.DBGFUNC("");
        if (!hasThread(th.getOwner()) && neighbourHasThread(th.getOwner())) {
            Debug.DBGFUNC("added thread successfully");
            return threads.add(th);
        }
        Debug.DBGFUNC("cant add thread: hasThread=" + hasThread(th.getOwner()) + " neighbourHasThread=" + neighbourHasThread(th.getOwner()));
        return false;
    }

    /**
     * Eltávolít egy gombafonalat a tektonról
     * @param th Az eltávolítandó gombafonál
     * @return Az remove sikeressége (bool)
     */
    @Override
    public boolean remove(MushroomThread th) {
        return threads.remove(th);
    }

    /**
     * Rárak egy gombatestet a tektonra
     * @param th A rárakandó gombatest
     * @return Az add sikeressége (bool)
     */
    @Override
    public boolean add(MushroomStem ms) {
        // we need to clone this because we remove while iterating later
        List<Spore> sp = new ArrayList<>(getSpores(ms.getOwner()));

        if (stem == null && hasThread(ms.getOwner()) && sp.size() >= ms.getCost()) {
            for (int i = 0; i < ms.getCost(); i++)
                sp.get(i).remove();

            stem = ms;

            Debug.DBGFUNC("stem added successfully");
            return true;
        }

        Debug.DBGFUNC("stem cant be added: hasThread=" + hasThread(ms.getOwner()) + " spores=" + sp.size());
        return false;
    }

    /**
     * Eltávolít egy gombatestet a tektonról
     * @param th A eltávolítandó gombatest
     * @return A remove sikeressége (bool)
     */
    @Override
    public boolean remove(MushroomStem ms) {
        stem = null;
        return true;
    }

    /**
     * Hozzáad egy spórát a tektonon lévő spórákhoz
     * @param sp A hozzáadott spóra
     * @return Az add sikeressége (bool)
     */
    @Override
    public boolean add(Spore sp) {
        return spores.add(sp);
    }

    /**
     * Eltávolít egy spórát a tektonról
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
}
