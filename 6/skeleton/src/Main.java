
import java.util.Scanner;

import insect.Insect;
import insect.Insecter;
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

    static void TEST_moveInsect(Map m, Insecter i) {
        DBG_printMap(m);
        System.out.println("Select a tecton to move to: ");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        if (num >= m.getTectons().size() || num < 0) {
            System.out.println("Invalid tecton index!");
            return;
        }

        i.move(m.getTectons().get(num));
    }

    static void TEST_cutInsect(Map m, Insecter i) {
        Insect insect = i.getInsect();
        Tecton t = insect.getLocation();
        System.out.println("Select thread: 0-" + (t.getThreads().size()-1));
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        if (num < 0 || num >= t.getThreads().size()) {
            System.out.println("Invalid thread index!");
            return;
        }

        i.cut(t.getThreads().get(num));
    }

    static void TEST_eatInsect(Map m, Insecter i) {
        Insect insect = i.getInsect();
        Tecton t = insect.getLocation();
        System.out.println("Select spore: 0-" + (t.getSpores().size()-1));
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        if (num < 0 || num >= t.getSpores().size()) {
            System.out.println("Invalid spore index!");
            return;
        }

        i.eat(t.getSpores().get(num));
    }

    static Insecter chooseInsecter(Insecter a, Insecter b) {
        System.out.println("Select insecter (0/1)");
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        if (i == 0) {
            return a;
        } else {
            return b;
        }
    }

    public static void main(String[] args) {
        Map map = new Map();
        System.out.println("Generating map");
        map.generate();
        DBG_printMap(map);

        System.out.println("Creating insecters");
        Insecter insecter1 = new Insecter();
        Insect insect1 = new Insect();
        insecter1.setInsect(insect1);

        Insecter insecter2 = new Insecter();
        Insect insect2 = new Insect();
        insecter2.setInsect(insect2);
        
        System.out.println("Creating mushroomers");
    
        Scanner in = new Scanner(System.in);

        int num = 0;
        while(num != 10) {
            System.out.println("Select test case:");
            System.out.println(" 0. Move with insect");
            System.out.println(" 1. Cut with insect");
            System.out.println(" 2. Eat with insect");
            System.out.println(" 10. Exit");

            num = in.nextInt();

            switch (num) {
                case 0:
                    
                    TEST_moveInsect(map, chooseInsecter(insecter1, insecter2)); 
                    break;
                case 1:
                    TEST_cutInsect(map, chooseInsecter(insecter1, insecter2));
                    break;
                case 2:
                    TEST_eatInsect(map, chooseInsecter(insecter1, insecter2));
                    break;
                case 10:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid test case!");
                    break;
            }
        }
    }
}
