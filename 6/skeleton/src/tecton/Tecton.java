package tecton;

import core.*;
import insect.*;
import mushroom.*;
import mushroom.spore.*;
import java.util.*;

public class Tecton implements IRound, ISpore, IStem, IThread {
    protected MushroomStem stem;
    protected List<Spore> spores;
    protected List<MushroomThread> threads;
    protected List<Tecton> neighbours;
    protected List<Insect> insects;

    public Tecton() {
        neighbours = new ArrayList<Tecton>();
    }

    public Integer getSporeCount(Player p) {
        return spores.stream().filter(sp -> sp.getOwner() == p).toArray().length;
    }

    public Tecton split() {
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

    public List<Tecton> getNeighbours() {
        return neighbours;
    }

    public List<MushroomThread> getThreads() {
        return threads;
    }

    public List<Spore> getSpores() {
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

    @Override
    public boolean add(MushroomThread th) {
        if (neighbours.stream()
                .filter(t -> t.getThreads().stream().filter(m -> m.getOwner() == th.getOwner()).toArray().length > 0)
                .toArray().length > 0) {
            threads.add(th);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(MushroomThread th) {
        return threads.remove(th);
    }

    @Override
    public boolean add(MushroomStem ms) {
        if (threads.stream().filter(m -> m.getOwner() == ms.getOwner()).toArray().length > 0) {
            stem = ms;
            return true;
        }
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
