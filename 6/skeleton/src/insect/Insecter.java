package insect;

import core.Player;
import mushroom.MushroomThread;
import mushroom.spore.Spore;
import tecton.Tecton;

public class Insecter extends Player implements IInsect {
    private Insect insect;

    public Insect getInsect() {
        return insect;
    }

    public void setInsect(Insect insect) {
        this.insect = insect;
    }

    @Override
    public void endTurn() {
        // Implementation for ending the turn
    }

    @Override
    public Integer getScore() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScore'");
    }

    @Override
    public void addScore(Integer score) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setScore'");
    }

    @Override
    public boolean eat(Spore sp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eat'");
    }

    @Override
    public boolean move(Tecton t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public boolean cut(MushroomThread th) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cut'");
    }
}