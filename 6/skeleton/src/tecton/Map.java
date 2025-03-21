package tecton;
import java.util.*;

public class Map {
    List<Tecton> tectons;

    public Map() {
        tectons = new ArrayList<Tecton>();
    }

    public List<Tecton> getTectons() {
        return tectons;
    };

    void connect(Tecton a, Tecton b) {
        a.addNeighbour(b);
        b.addNeighbour(a);
    }

    public void generate() {
        System.out.println("Map: generate()");


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
}
