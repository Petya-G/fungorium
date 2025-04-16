package model.insect;

import model.mushroom.MushroomThread;
import model.mushroom.spore.Spore;

import java.util.ArrayList;
import java.util.List;

import model.core.Player;
import model.tecton.Tecton;

/**
 * Az Insecter osztály egy játékos, aki egy rovart irányít.
 * Ez az osztály implementálja az IInsect interfészt, hogy a játékos képes legyen spórákat enni,
 * mozogni és gombafonalakat elvágni.
 */
public class Insecter extends Player implements IInsect {
    private List<Insect> insects = new ArrayList<Insect>();

    public Insecter(Tecton location) {
        this.insects.add(new Insect(this, location));
    }

    public Insecter(List<Insect> insects) {
        this.insects = insects;
    }

    public boolean eatWith(Insect insect, Spore sp) {
        return insect.eat(sp);
    }

    public boolean moveWith(Insect insect, Tecton t) {
        return insect.move(t);
    }

    public boolean cutWith(Insect insect, MushroomThread th) {
        return insect.cut(th);
    }

    public boolean createInsect(Tecton location) {
        return add(new Insect(this, location));
    }

    /**
     * A kör végén végrehajtandó műveletek.
     */
    @Override
    public void endTurn() {
        insects.forEach(Insect::endTurn);
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
}