package insect;

import mushroom.MushroomThread;
import mushroom.spore.Spore;
import core.Debug;
import core.Player;
import tecton.Tecton;

public class Insecter extends Player implements IInsect {
    private Insect insect;

    /**
     * A rovarászhoz tartozó rovar lekérése
     */
    public Insect getInsect() {
        return insect;
    }

    /**
     * Rovar rendelése a rovarászhoz
     */
    public void setInsect(Insect insect) {
        this.insect = insect;
    }

    @Override
    public void endTurn() {
        System.out.println("Spora megevése");
    }

    @Override
    public Integer getScore() {
        return score;
    }

    @Override
    public void addScore(Integer score) {
        score++;
        Debug.DBGFUNC("Pont megnövelve");
    }

    @Override
    public boolean eat(Spore sp) {
        return insect.eat(sp);
        
    }

    @Override
    public boolean move(Tecton t) {
        return insect.move(t);
    }

    @Override
    public boolean cut(MushroomThread th) {
        return insect.cut(th);
    }
}