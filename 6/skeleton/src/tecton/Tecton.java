package tecton;

import java.util.List;

import core.*;
import insect.*;
import mushroom.*;
import mushroom.spore.*;

public class Tecton implements IRound, ISpore, IStem, IThread {
    protected MushroomStem stem;
    protected List<Spore> spores;
    protected List<MushroomThread> threads;
    protected List<Tecton> neighbours;
    protected List<Insect> insects;

    public Integer getSporeCount(Player p){
        throw new UnsupportedOperationException("Unimplemented method 'endRound'");
    }
    public Boolean hasStem(){
        throw new UnsupportedOperationException("Unimplemented method 'endRound'");
    }
    public void addNeighbour(Tecton t){}
    public void addInsect(Insect i){}

    @Override
    public void endRound() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endRound'");
    }
    @Override
    public boolean add(MushroomThread th) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
    @Override
    public boolean remove(MushroomThread th) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
    @Override
    public boolean add(MushroomStem ms) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
    @Override
    public boolean remove(MushroomStem ms) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
    @Override
    public boolean add(Spore sp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
    @Override
    public boolean remove(Spore sp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
}
