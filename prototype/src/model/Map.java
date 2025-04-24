package model;

import controller.Game;
import model.core.IRound;
import model.tecton.LifeSupportTecton;
import model.tecton.SingleThreadedTecton;
import model.tecton.StemlessTecton;
import model.tecton.Tecton;
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

    private boolean generated = false;
    public Map() {
        
    }
    public boolean isGenerated() {
        return generated;
    }

    public void generate(Random rand) {
        generated = true;

        for (int i = 0; i < rand.nextInt(5, 15); i++) {
            switch (rand.nextInt(5)) {
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
                    tectons.add(new StemlessTecton());
                    break;
                case 4:
                    tectons.add(new ThreadConsumingTecton());
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < tectons.size(); i++) {
            for (int j = i+1; j < tectons.size(); j++) {
                if (rand.nextBoolean()) {
                    connect(tectons.get(i), tectons.get(j));
                }
            }
        }
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

    public void printSelf()
    {
        for (Tecton tecton : tectons) {
            String help = tecton.getName() + tecton.getId()+": ";
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
}