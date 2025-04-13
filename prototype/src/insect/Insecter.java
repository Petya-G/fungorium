package insect;

import mushroom.MushroomThread;
import mushroom.spore.Spore;

import java.util.ArrayList;
import java.util.List;

import core.Debug;
import core.Player;
import tecton.Tecton;

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
        Debug.DBGFUNC("Spóra megevése");
        return insect.eat(sp);
    }

    public boolean moveWith(Insect insect, Tecton t) {
        Debug.DBGFUNC("Rovarász mozog");
        return insect.move(t);
    }

    public boolean cutWith(Insect insect, MushroomThread th) {
        Debug.DBGFUNC("Rovarász gombafonalat vág");
        return insect.cut(th);
    }

    /**
     * A kör végén végrehajtandó műveletek.
     */
    @Override
    public void endTurn() {
        insects.forEach(Insect::endTurn);
        Debug.DBGFUNC("Kör vége");
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