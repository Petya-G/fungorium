package mushroom;

import java.util.*;

import mushroom.spore.*;
import core.Debug;
import core.Player;
import tecton.*;

public class Mushroomer extends Player implements ISpore, IStem, IThread {
    private List<Spore> spores;
    private List<MushroomStem> stems;
    private List<MushroomThread> threads;

    public List<MushroomStem> getStems() {
        return stems;
    }

    public Mushroomer(List<Spore> spores, List<MushroomStem> stems, List<MushroomThread> threads) {
        this.spores = spores;
        this.stems = stems;
        this.threads = threads;
    }

    public Mushroomer() {
        this.spores = new ArrayList<>();
        this.stems = new ArrayList<>();
        this.threads = new ArrayList<>();
    }

    public List<MushroomThread> getThreads() {
        return threads;
    }

    public Boolean plantMushroomstem(Tecton tecton) {
        Debug.DBGFUNC("");
        MushroomStem ms = new MushroomStem(this, tecton);
        return tecton.add(ms) ? add(ms) : false;
    }

    public Boolean growMushroomthread(Tecton tecton) {
        Debug.DBGFUNC("");
        MushroomThread mt = new MushroomThread(this, tecton);
        return tecton.add(mt) ? add(mt) : false;
    }

    public Boolean throwSpore(MushroomStem ms, Tecton tecton) {
        Debug.DBGFUNC("");
        return ms.throwSpore(tecton);
    }

    public Boolean levelUp(MushroomStem ms) {
        Debug.DBGFUNC("");
        return ms.levelUp();
    }

    @Override
    public boolean add(MushroomThread th) {
        return threads.add(th);
    }

    @Override
    public boolean remove(MushroomThread th) {
        return threads.remove(th);
    }

    @Override
    public boolean add(MushroomStem ms) {
        Debug.DBGFUNC("");
        return stems.add(ms);
    }

    @Override
    public boolean remove(MushroomStem ms) {
        return stems.remove(ms);
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
    public Integer getScore() {
        return score;
    }

    @Override
    public void addScore(Integer score) {
        this.score += score;
    }

    @Override
    public void endTurn() {
        stems.forEach(ms -> ms.endTurn());
        threads.forEach(th -> th.endTurn());
        spores.forEach(sp -> sp.endTurn());
    }
}