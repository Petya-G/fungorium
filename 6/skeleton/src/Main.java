
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
                    System.out.println("Select insecter (0/1)");
                    int i = in.nextInt();
                    if (i == 0) {
                        TEST_moveInsect(map,insecter1);
                    } else {
                        TEST_moveInsect(map,insecter2);
                    } 
                    break;
                case 10:
                    System.out.println("Goodbye!");
                default:
                    System.out.println("Invalid test case!");
                    break;
            }
        }
    }
}
