package insect;

import mushroom.MushroomThread;
import mushroom.spore.Spore;
import core.Debug;
import core.Player;
import tecton.Tecton;

/**
 * Az Insecter osztály egy játékos, aki egy rovart irányít.
 * Ez az osztály implementálja az IInsect interfészt, hogy a játékos képes legyen spórákat enni,
 * mozogni és gombafonalakat elvágni.
 */
public class Insecter extends Player implements IInsect {
    private Insect insect;

    /**
     * A rovarászhoz tartozó rovart adja vissza.
     * @return A rovarászhoz tartozó rovar.
     */
    public Insect getInsect() {
        Debug.DBGFUNC("Rovarászhoz tartozó rovar");
        return insect;
    }

    /**
     * Rovart rendel a rovarászhoz.
     * @param insect A rovar, amelyet a rovarászhoz rendelünk.
     */
    public void setInsect(Insect insect) {
        Debug.DBGFUNC("Rovarászhoz tartozó rovar beállítása");
        this.insect = insect;
    }

    /**
     * A kör végén végrehajtandó műveletek.
     */
    @Override
    public void endTurn() {
        Debug.DBGFUNC("Kör vége");
    }

    /**
     * Visszaadja a rovarász pontszámát.
     * @return A rovarász pontszáma.
     */
    @Override
    public Integer getScore() {
        Debug.DBGFUNC("Rovarász pontszáma");
        return score;
    }

    /**
     * Növeli a játékos pontszámát.
     * @param s A hozzáadandó pontok száma.
     */
    @Override
    public void addScore(Integer s) {
        Debug.DBGFUNC("Pont megnövelve");
        score+=s;
    }

    /**
     * A rovarász megpróbál elfogyasztani egy gombaspórát.
     * @param sp Az elfogyasztandó spóra.
     * @return Igaz, ha sikerült megenni a spórát, egyébként hamis.
     */
    @Override
    public boolean eat(Spore sp) {
        Debug.DBGFUNC("Spóra megevése");
        return insect.eat(sp);
    }

    /**
     * A rovarász megpróbál átmenni egy másik tektonra.
     * @param t A tekton, ahová a rovarász menni szeretne.
     * @return Igaz, ha a mozgás sikerült, egyébként hamis.
     */
    @Override
    public boolean move(Tecton t) {
        Debug.DBGFUNC("Rovarász mozog");
        return insect.move(t);
    }

    /**
     * A rovarász megpróbál elvágni egy gombafonalat.
     * @param th Az elvágandó gombafonal.
     * @return Igaz, ha sikerült elvágni a fonalat, egyébként hamis.
     */
    @Override
    public boolean cut(MushroomThread th) {
        Debug.DBGFUNC("Rovarász gombafonalat vág");
        return insect.cut(th);
    }
}