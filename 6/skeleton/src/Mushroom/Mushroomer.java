package mushroom;

import java.util.List;

import core.Player;
import mushroom.spore.*;
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

    public Boolean plantMushroomstem(Tecton tecton){
        MushroomStem ms = new MushroomStem();
        if(tecton.add(ms)){
            add(ms);
            return true;
        }
        return false;
    }

    public Boolean growMushroomthread(Tecton tecton){
        MushroomThread mt = new MushroomThread();
        if(tecton.add(mt)){
            add(mt);
            return true;
        }
        return false;
    }

    public Boolean throwSpore(MushroomStem ms, Tecton tecton){
        if(ms.throwSpore(tecton))
            return true;
        return false;
    }

    public Boolean levelUp(MushroomStem ms) {
        if(ms.levelUp())
            return true;
        return false;
    }

    @Override
    public boolean add(MushroomThread th) {
        threads.add(th);
        return true;
    }

    @Override
    public boolean remove(MushroomThread th) {
        threads.remove(th);
        return true;
    }

    @Override
    public boolean add(MushroomStem ms) {
        stems.add(ms);
        return true;
    }

    @Override
    public boolean remove(MushroomStem ms) {
        stems.remove(ms);
        return true;
    }

    @Override
    public boolean add(Spore sp) {
        spores.add(sp);
        return true;
    }

    @Override
    public boolean remove(Spore sp) {
        spores.remove(sp);
        return true;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endTurn'");
    }
}