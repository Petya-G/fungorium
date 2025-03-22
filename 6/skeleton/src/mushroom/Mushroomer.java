package Mushroom;

import java.util.List;

import Mushroom.spore.*;
import core.Player;
import tecton.*;

public class Mushroomer extends Player implements ISpore, IStem, IThread {
    private List<Spore> spores;
    private List<MushroomStem> stems;
    private List<MushroomThread> threads;

    public Mushroomer(List<Spore> spores, List<MushroomStem> stems, List<MushroomThread> threads) {
        this.spores = spores;
        this.stems = stems;
        this.threads = threads;
    }

    public Boolean plantMushroomstem(Tecton tecton) {
        MushroomStem ms = new MushroomStem(this, tecton);
        return tecton.add(ms) ? add(ms) : false;
    }

    public Boolean growMushroomthread(Tecton tecton) {
        MushroomThread mt = new MushroomThread(this, tecton);
        return tecton.add(mt) ? add(mt) : false;
    }

    public Boolean throwSpore(MushroomStem ms, Tecton tecton) {
        return ms.throwSpore(tecton);
    }

    public Boolean levelUp(MushroomStem ms) {
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