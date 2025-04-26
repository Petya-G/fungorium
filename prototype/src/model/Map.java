package model;

import controller.Game;
import model.core.IRound;
import model.tecton.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * A pályát reprezentáló osztály, amely tartalmazza a pályán lévő tektonokat.
 * Kezeli a tektonok összekötését, valamint körök végén a hozzájuk tartozó műveleteket.
 */
public class Map implements IRound, Serializable {
    /**
     * Pályához tartozó tektonok
     */
    public List<Tecton> tectons = new ArrayList<>();

    public Map() {

    }

    public void generate(int size) {
        for (int i = 0; i < size; i++) {
            switch (Game.random.nextInt(4)) {
                case 0:
                    tectons.add(new Tecton());
                    break;
                case 1:
                    tectons.add(new LifeSupportTecton());
                    break;
                case 2:
                    tectons.add(new SingleThreadedTecton());
                    break;
                case 3:
                    tectons.add(new ThreadConsumingTecton());
                    break;
            }
        }

        for (int i = 0; i < tectons.size(); i++) {
            for (int j = i + 1; j < tectons.size(); j++) {
                if (Game.random.nextBoolean()) {
                    connect(tectons.get(i), tectons.get(j));
                }
            }
        }
    }

    // teszt pálya, nagyon egyszerű de annál sokszínűbb :)
    public void genTestMap() {
        // 5 fajta tekton
        tectons.add(new Tecton());
        tectons.add(new LifeSupportTecton());
        tectons.add(new SingleThreadedTecton());
        tectons.add(new StemlessTecton());
        tectons.add(new ThreadConsumingTecton());

        // szépen sorba kötve
        connect(tectons.get(0), tectons.get(1));
        connect(tectons.get(1), tectons.get(2));
        connect(tectons.get(2), tectons.get(3));
        connect(tectons.get(3), tectons.get(4));


    }

    /**
     * Összeköt két tektont a pályán, szomszédossá téve őket.
     *
     * @param a Egyik összekötendő tekton
     * @param b Másik összekötendő tekton
     */
    public static void connect(Tecton a, Tecton b) {
        a.addNeighbour(b);
        b.addNeighbour(a);
    }

    public void printSelf() {
        for (Tecton tecton : tectons) {
            String help = tecton.getName() + tecton.getId() + ": ";
            for (Tecton neighbour : tecton.getNeighbours()) {
                help += neighbour.getName() + neighbour.getId() + ", ";
            }
            System.out.println(help.substring(0, help.lastIndexOf(',')));
        }
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

    @Override
    public String toString() {
        return super.toString() + " tectons=" + tectons;
    }
}