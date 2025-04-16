package model;

import java.util.*;

import model.tecton.*;
import model.core.*;

public class Map implements IRound {
    public List<Tecton> tectons; // a model.Map tektonjait tartalmazó lista

    /**
     * Konstruktor, üresen inicializálja a tectons tagváltozót
     */
    public Map() {
        tectons = new ArrayList<Tecton>();
    }

    /**
     * Összeköt két tektont a pályán
     *
     * @param a Egyik összekötendő tekton
     * @param b Másik összekötendő tekton
     */
    void connect(Tecton a, Tecton b) {
        a.addNeighbour(b);
        b.addNeighbour(a);
    }

    /**
     * Létrehozza a pályát. Jelenleg teszteléshez egy fix pálya jön létre, ez később
     * random lesz
     */
    public void generate() {
        // test map generation. Final version will feature randomly generated maps

        Tecton t1 = new Tecton();
        Tecton t2 = new Tecton();
        StemlessTecton t3 = new StemlessTecton();
        ThreadConsumingTecton t4 = new ThreadConsumingTecton();
        SingleThreadedTecton t5 = new SingleThreadedTecton();

        tectons.add(t1);
        tectons.add(t2);
        tectons.add(t3);
        tectons.add(t4);
        tectons.add(t5);

        // big_diagram 2 alapjan, a kapcsolatok szimmetrukusak kell hogy legyenek
        connect(t4, t5);

        connect(t4, t2);

        connect(t3, t4);

        connect(t2, t3);

        connect(t1, t2);
    }

    /**
     * Round vége, itt minden tekton EndRound függvényét meghívjuk
     */
    @Override
    public void endRound() {
        tectons.forEach(t -> t.endRound());
        for (Tecton t : tectons) {
            if (new Random().nextBoolean()) {
                tectons.add(t.split());
            }
        }
    }
}
