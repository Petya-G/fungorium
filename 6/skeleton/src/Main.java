
import java.util.Scanner;

import core.Debug;
import core.Debug.*;
import insect.Insect;
import insect.Insecter;
import mushroom.*;
import mushroom.spore.SpeedingSpore;
import mushroom.spore.Spore;
import tecton.*;
public class Main {
    
    /**
     * Kiírja a pályán lévő tektonokat és kapcsolataikat
     * @param m A pálya, vagy térkép, amit ki írunk
     */
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

    /**
     * A rovar mozgatását teszteli
     * @param m A pálya, amin van és mozog a rovar
     * @param i A rovar, amit mozgatunk
     */
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

    /**
     * A rovarral való fonalvágást teszteli
     * @param m A pálya, amin van a rovar
     * @param i A rovar, amivel elvágjuk a fonalat
     */
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

    /**
     * A spórák megevését teszteli
     * @param m A pálya, amin van a rovar
     * @param i A rovar, ami megeszi a spórát
     */
    static void TEST_eatInsect(Map m, Insecter i) {
        Insect insect = i.getInsect();
        Tecton t = insect.getLocation();

        if (t.getSpores().size() < 1) {
            System.out.println("No spores to eat!");
            return;
        }

        System.out.println("Select spore: 0-" + (t.getSpores().size()-1));
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        if (num < 0 || num >= t.getSpores().size()) {
            System.out.println("Invalid spore index!");
            return;
        }

        i.eat(t.getSpores().get(num));
    }

    /**
     *Az új fonál növesztését tesztelő függvény
     * @param m A pálya, amin fonalat növesztünk
     * @param mr A gombász, aki növeszti az új fonalat
     */
    static void TEST_growThread(Map m, Mushroomer mr) {
        DBG_printMap(m);
        System.out.println("Select a tecton: ");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        if (num >= m.getTectons().size() || num < 0) {
            System.out.println("Invalid tecton index!");
            return;
        }

        mr.growMushroomthread(m.getTectons().get(num));
    }

    /**
     * Új Stem ültetését teszteli
     * @param m A pálya, amin gombatestet (Stem) növesztünk
     * @param mr A gombász, aki növeszti az új gombatestet (Stem)
     */
    static void TEST_plantStem(Map m, Mushroomer mr) {
        DBG_printMap(m);
        System.out.println("Select a tecton: ");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        if (num >= m.getTectons().size() || num < 0) {
            System.out.println("Invalid tecton index!");
            return;
        }

        mr.plantMushroomstem(m.getTectons().get(num));
    }

    /**
     * Spórák dobását teszteli
     * @param m A pálya, amin spórát dobunk
     * @param mr A gombász, aki spórát dob
     */
    static void TEST_throwSpore(Map m, Mushroomer mr) {
        DBG_printMap(m);
        System.out.println("Select a tecton: ");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        if (num >= m.getTectons().size() || num < 0) {
            System.out.println("Invalid tecton index!");
            return;
        }
        if (mr.getStems().size() < 1) {
            System.out.println("Mushroomer has no stems!");
            return;
        }

        System.out.println("Select stem: 0-" + (mr.getStems().size()-1));
       
        int num2 = in.nextInt();

        if (num2 < 0 || num2 >= mr.getStems().size()) {
            System.out.println("Invalid stem index!");
            return;
        }

        mr.throwSpore(mr.getStems().get(num2), m.getTectons().get(num));
    }

    /**
     * Gombatest fejlesztését teszteli
     * @param m A pálya, amin van a gombatest
     * @param mr A gombász, akié a gombatest
     */
    static void TEST_levelStem(Map m, Mushroomer mr) {
        

        System.out.println("Select stem: 0-" + (mr.getStems().size()-1));

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        if (num < 0 || num >= mr.getStems().size()) {
            System.out.println("Invalid stem index!");
            return;
        }

        mr.levelUp(mr.getStems().get(num));
    }

    /**
     * Megkéri a felhasználót, hogy két Insecter közül válasszon (teszteléshez)
     * @param a Az első Insecter
     * @param b A második Insecter
     * @return A kiválasztott Insecter
     */
    static Insecter chooseInsecter(Insecter a, Insecter b) {
        System.out.println("Select insecter (0/1)");
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        in.close();
        if (i == 0) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Megkéri a felhasználót, hogy két Mushroomer közül válasszon (teszteléshez)
     * @param a Az első Mushroomer
     * @param b A második Mushroomer
     * @return A kiválasztott Mushroomer
     */
    static Mushroomer chooseMushroomer(Mushroomer a, Mushroomer b) {
        System.out.println("Select mushroomer (0/1)");
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        if (i == 0) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * program belépési pont, jelenleg a tesztelési menüt irányítja, illetve létrehozza a tesztpályát és a tesztjátékosokat
     */
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

        Mushroomer mushroomer1 = new Mushroomer();
        Mushroomer mushroomer2 = new Mushroomer();


        // create test stem
        Tecton t0 =  map.tectons.get(0);

        MushroomThread thread1 = new MushroomThread(mushroomer1, t0);
        mushroomer1.getThreads().add(thread1);
        t0.getThreads().add(thread1);

        MushroomStem stem1 = new MushroomStem(mushroomer1,t0);
        mushroomer1.getStems().add(stem1);
        t0.setStem(stem1);

        System.out.println("Creating mushroomers");
    
        Scanner in = new Scanner(System.in);

        int num = 0;
        while(num != 10) {
            System.out.println("Select test case:");
            System.out.println(" 0. Move with insect");
            System.out.println(" 1. Cut with insect");
            System.out.println(" 2. Eat with insect");

            System.out.println(" 3. Grow thread on tecton");
            System.out.println(" 4. Plant stem on tecton");
            System.out.println(" 5. Throw spore");
            System.out.println(" 6. End turn");
            System.out.println(" 7. End round");
            System.out.println(" 8. Level up stem (?)");
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
                case 3:
                    TEST_growThread(map, chooseMushroomer(mushroomer1, mushroomer2));
                    break;
                case 4:
                    TEST_plantStem(map, chooseMushroomer(mushroomer1, mushroomer2));
                    break;
                case 5:
                    TEST_throwSpore(map, chooseMushroomer(mushroomer1, mushroomer2));
                    break;
                case 6:
                    mushroomer1.endTurn();
                    mushroomer2.endTurn();
                    insecter1.endTurn();
                    insect2.endTurn();

                    break;
                case 7:
                    map.endRound();
                    
                    break;
                case 8:
                    TEST_levelStem(map, chooseMushroomer(mushroomer1, mushroomer2));
                    
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
