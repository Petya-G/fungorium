
import tecton.*;
public class Main {
    
    static void DBG_printMap(Map m) {
        System.out.println("--- Map ---");
        int c = 0;
        for (Tecton t : m.getTectons()) {
            System.out.print(c + ": " + t.toString() + " connected to: ");

            for (Tecton n : t.getNeighbours()) {
                int n_index = m.getTectons().indexOf(n);
                System.out.print(n_index + " ");
            }
            System.out.println("");
            c++;
        }
        System.out.println("--- *** ---");
    } 

    public static void main(String[] args) {
        Map map = new Map();
        System.out.println("Generating map");
        map.generate();
        DBG_printMap(map);
    }
}
