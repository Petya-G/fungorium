package model;

import controller.Game;
import model.core.IRound;
import model.tecton.Tecton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A pályát reprezentáló osztály, amely tartalmazza a pályán lévő tektonokat.
 * Kezeli a tektonok összekötését, valamint körök végén a hozzájuk tartozó műveleteket.
 */
public class Map implements IRound, Serializable {
    /**
     * Pályához tartozó tektonok
     */
    public List<Tecton> tectons = new ArrayList<>();

    /**
     * Összeköt két tektont a pályán, szomszédossá téve őket.
     *
     * @param a Egyik összekötendő tekton
     * @param b Másik összekötendő tekton
     */
    void connect(Tecton a, Tecton b) {
        a.addNeighbour(b);
        b.addNeighbour(a);
    }

    /**
     * A kör végén minden tekton végrehajtja saját EndRound műveletét és véletlenszerűen új tektonok jöhetnek létre.
     */
    @Override
    public void endRound() {
        tectons.forEach(Tecton::endRound);
        for (Tecton t : tectons) {
            if (Game.random.nextBoolean()) {
                tectons.add(t.split());
            }
        }
    }

    /**
     * Meghatározza, hogy két pálya azonos-e a bennük található tektontok alapján.
     *
     * @param o Másik összehasonlítandó objektum
     * @return true, ha a két pálya megegyezik
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return Objects.equals(tectons, map.tectons);
    }

    /**
     * Visszaadja a pálya hash-kódját a tektontok szerint
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(tectons);
    }
}