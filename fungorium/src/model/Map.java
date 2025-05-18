package model;

import controller.visitor.NewObjectVisitor;
import model.core.IRound;
import model.tecton.*;

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

    public List<Tecton> getTectons() {
        return tectons;
    }

    public Map() {

    }

    public void add(Tecton tecton){
        tectons.add(tecton);
        NewObjectVisitor newObjectVisitor = new NewObjectVisitor();
        tecton.accept(newObjectVisitor);
    }

    public void generate(int size) {
        System.out.println("generating map with size: " + size);
        for (int i = 0; i < size; i++) {
            switch (Game.random.nextInt(4)) {
                case 0:
                    add(new Tecton());
                    break;
                case 1:
                    add(new LifeSupportTecton());
                    break;
                case 2:
                    add(new SingleThreadedTecton());
                    break;
                case 3:
                    add(new ThreadConsumingTecton());
                    break;
            }
            tectons.getLast().setPosX(Game.random.nextDouble());
            tectons.getLast().setPosY(Game.random.nextDouble());
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
        add(new Tecton());
        tectons.getLast().setPosX(0);
        tectons.getLast().setPosY(0);

        add(new LifeSupportTecton());
        tectons.getLast().setPosX(0);
        tectons.getLast().setPosY(1);

        add(new SingleThreadedTecton());
        tectons.getLast().setPosX(1);
        tectons.getLast().setPosY(0);

        add(new StemlessTecton());
        tectons.getLast().setPosX(1);
        tectons.getLast().setPosY(0.5);

        add(new ThreadConsumingTecton());
        tectons.getLast().setPosX(0.5);
        tectons.getLast().setPosY(1);

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

    private boolean enableSplitting = true;

    public boolean isSplittingEnabled() {
        return enableSplitting;
    }

    public void setSplittingEnabled(boolean b) {
        enableSplitting = b;
    }

    /**
     * A kör végén minden tekton végrehajtja saját EndRound műveletét és véletlenszerűen új tektonok jöhetnek létre.
     */
    @Override
    public void endRound() {
        ArrayList<Tecton> tectonsClone = new ArrayList<>(tectons);
        tectonsClone.forEach(Tecton::endRound);

        System.out.println("end of round. Splitting is [" + enableSplitting + "]");
        if (enableSplitting) {
            for (Tecton t : tectonsClone) {
                if (Game.random.nextInt(10) < 1) {
                    add(t.split());
                }
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