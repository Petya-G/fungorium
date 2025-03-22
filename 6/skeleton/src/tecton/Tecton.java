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

    public Tecton() {
        neighbours = new ArrayList<Tecton>();
        threads = new ArrayList<>();
        spores = new ArrayList<>();
        insects = new ArrayList<>();
        
        Debug.DBGFUNC("Tecton lerakva");
    }

    public Integer getSporeCount(Player p) {
        return spores.stream().filter(sp -> sp.getOwner() == p).toArray().length;
    }

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

    public void setStem(MushroomStem st) {
        stem = st;
    }

    public List<Tecton> getNeighbours() {
        return neighbours;
    }

    public List<MushroomThread> getThreads() {
        return threads;
    }

    public List<Spore> getSpores() {
        return spores;
    }

    public List<Spore> getSpores(Player owner) {
        return spores;
    }

    public void addNeighbour(Tecton t) {
        neighbours.add(t);
    }

    public void addInsect(Insect i) {
        insects.add(i);
    }

    public void removeInsect(Insect i) {
        insects.remove(i);
    }

    public boolean hasThread(Player p) {
        return threads.stream().filter(m -> m.getOwner() == p).toArray().length == 1;
    }

    public boolean neighbourHasThread(Player p) {
        return neighbours.stream().filter(t -> t.hasThread(p)).toArray().length > 0;
    }

    @Override
    public boolean add(MushroomThread th) {
        if (!hasThread(th.getOwner()) && neighbourHasThread(th.getOwner()))
            return threads.add(th);
        return false;
    }

    @Override
    public boolean remove(MushroomThread th) {
        return threads.remove(th);
    }

    @Override
    public boolean add(MushroomStem ms) {
        List<Spore> sp = getSpores(ms.getOwner());

        if (stem == null && hasThread(ms.getOwner()) && sp.size() >= ms.getCost()) {
            for (int i = 0; i < ms.getCost(); i++)
                sp.get(i).remove();

            stem = ms;

            Debug.DBGFUNC("stem added successfully");
            return true;
        }

        Debug.DBGFUNC("stem cant be added");
        return false;
    }

    @Override
    public boolean remove(MushroomStem ms) {
        stem = null;
        return true;
    }

    @Override
    public boolean add(Spore sp) {
        return spores.add(sp);
    }

    @Override
    public boolean remove(Spore sp) {
        return spores.remove(sp);
    }

    @Override
    public void endRound() {
    }
}
