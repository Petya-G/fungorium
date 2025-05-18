package model;

import controller.Controller;
import model.core.*;
import model.insect.Insect;
import model.insect.Insecter;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.mushroom.spore.*;
import model.tecton.Tecton;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A játékmenet kezeléséért felelős osztály, amely kezeli a köröket, a játékosokat
 * és a különböző műveleteket (pl. mozgás, támadás, növekedés).
 * Irányítja a játék logikáját, valamint nyilvántartja az aktuális játékállapotot.
 */
public class Game implements ITurn, IRound, Serializable {
    public static final Random random = new Random();

    /**
     * Egy körben lejátszható lépések maximális száma.
     */
    private final int maxRound = 10;
    /**
     * A játékban résztvevő játékosok listája.
     */
    private final List<Mushroomer> mushroomers = new ArrayList<>();
    private final List<Insecter> insecters = new ArrayList<>();
    /**
     * A játék pályája
     */
    private final Map map = new Map();
    /**
     * Nyilvántartja, elkezdődött-e már a játék
     */
    private boolean started = false;
    /**
     * Azt jelzi, hogy a játék véget ért-e.
     */
    private boolean ended = false;
    /**
     * Az aktuális kör sorszáma.
     */
    private int turn = 0;

    private int round = 0;

    public Game() {
    }

    public Map getMap() {
        return map;
    }

    /**
     * Ellenőrzi, hogy az adott entitás gazdája az aktuális játékos-e.
     *
     * @param entity Az entitás, amelyhez az ellenőrzést végezzük.
     * @return true, ha a gazda az aktuális játékos, egyébként false.
     */
    private boolean hasCurrentTurn(Entity entity) {
        return started && getCurrentPlayer().equals(entity.getOwner());
    }

    /**
     * Elindítja a játékot, a started változó beállításával
     */
    public void startGame(int playerCount) {
        if (started || ended || playerCount < 0 || playerCount > 8 || playerCount % 2 != 0) return;
        started = true;

        map.generate(playerCount * 2);

        for (int i = 0; i < playerCount / 2; i++) {
            mushroomers.add(new Mushroomer(map.tectons.get(random.nextInt(map.tectons.size()))));
            insecters.add(new Insecter(map.tectons.get(random.nextInt(map.tectons.size()))));
        }

    }

    public boolean startTestGame() {
        if (started || ended) return false;
        started = true;

        map.genTestMap();

        Mushroomer m1 = new Mushroomer(map.tectons.get(0));
        Insecter i1 = new Insecter(map.tectons.get(1));
        mushroomers.add(m1);
        insecters.add(i1);

        // legyen thread az 1-es tektonon is
        MushroomThread t1 = new MushroomThread(m1, map.tectons.get(1));
        map.tectons.get(1).add(t1);
        m1.add(t1);

        // legyen sok-sok spóra az 1-es tektonon
        SpeedingSpore sp0 = new SpeedingSpore(m1, map.tectons.get(1));
        map.tectons.get(1).add(sp0);
        m1.add(sp0);

        SlowingSpore sp1 = new SlowingSpore(m1, map.tectons.get(1));
        map.tectons.get(1).add(sp1);
        m1.add(sp1);

        ParalyzingSpore sp2 = new ParalyzingSpore(m1, map.tectons.get(1));
        map.tectons.get(1).add(sp2);
        m1.add(sp2);

        ClawParalyzingSpore sp3 = new ClawParalyzingSpore(m1, map.tectons.get(1));
        map.tectons.get(1).add(sp3);
        m1.add(sp3);

        SplitterSpore sp4 = new SplitterSpore(m1, map.tectons.get(1));
        map.tectons.get(1).add(sp4);
        m1.add(sp4);

        // legyen thread a 2-es tektonon is
        MushroomThread t2 = new MushroomThread(m1, map.tectons.get(2));
        map.tectons.get(2).add(t2);
        m1.add(t2);

        return true;
    }

    public boolean startTestGame2() {
        //TODO ez megjelenites teszthez van, ki lehet venni 
        if (started || ended) return false;
        started = true;

        map.genTestMap();


        Mushroomer m1 = new Mushroomer(map.tectons.get(0));
        Mushroomer m2 = new Mushroomer(map.tectons.get(1));
        Mushroomer m3 = new Mushroomer(map.tectons.get(2));
        Mushroomer m4 = new Mushroomer(map.tectons.get(4));

        Insecter i1 = new Insecter(map.tectons.get(1));
        Insecter i2 = new Insecter(map.tectons.get(1));
        Insecter i3 = new Insecter(map.tectons.get(1));
        Insecter i4 = new Insecter(map.tectons.get(1));


        mushroomers.add(m1);
        mushroomers.add(m2);
        mushroomers.add(m3);
        mushroomers.add(m4);

        insecters.add(i1);
        insecters.add(i2);
        insecters.add(i3);
        insecters.add(i4);

        // legyen thread az 1-es tektonon is
        MushroomThread t1 = new MushroomThread(m1, map.tectons.get(1));
        map.tectons.get(1).add(t1);
        m1.add(t1);

        // legyen sok-sok spóra az 1-es tektonon
        SpeedingSpore sp0 = new SpeedingSpore(m1, map.tectons.get(1));
        map.tectons.get(1).add(sp0);
        m1.add(sp0);

        SpeedingSpore sp10 = new SpeedingSpore(m2, map.tectons.get(4));
        map.tectons.get(4).add(sp10);
        m2.add(sp10);

        SlowingSpore sp1 = new SlowingSpore(m1, map.tectons.get(1));
        map.tectons.get(1).add(sp1);
        m1.add(sp1);

        ParalyzingSpore sp2 = new ParalyzingSpore(m1, map.tectons.get(1));
        map.tectons.get(1).add(sp2);
        m1.add(sp2);

        ClawParalyzingSpore sp3 = new ClawParalyzingSpore(m1, map.tectons.get(1));
        map.tectons.get(1).add(sp3);
        m1.add(sp3);

        SplitterSpore sp4 = new SplitterSpore(m1, map.tectons.get(1));
        map.tectons.get(1).add(sp4);
        m1.add(sp4);

        // legyen thread a 2-es tektonon is
        MushroomThread t2 = new MushroomThread(m1, map.tectons.get(2));
        map.tectons.get(2).add(t2);
        m1.add(t2);

        return true;
    }


    /**
     * Lekérdezi az aktuális körön lévő játékost.
     *
     * @return Az aktuális játékos.
     */
    public Player getCurrentPlayer() {
        if (!started) return null;

        if (turn % 2 == 0) {
            int mturn = turn / 2;
            return mushroomers.get(mturn % mushroomers.size());
        } else {
            int iturn = (turn - 1) / 2;
            return insecters.get(iturn % insecters.size());
        }
    }

    /**
     * Visszaadja a játék jelenlegi győzteseit, az első a gombászok közül, a második a rovarászok közül
     *
     * @return A legtöbb pontot elért játékosok, vagy null ha nincs ilyen.
     */
    public List<Player> getWinners() {
        if (!started) return null;
        List<Player> winners = new ArrayList<>();
        winners.add(insecters.stream().max(Comparator.comparingInt(Player::getScore)).orElse(null));
        winners.add(mushroomers.stream().max(Comparator.comparingInt(Player::getScore)).orElse(null));
        return winners;
    }

    /**
     * Egy rovar mozgatását kezdeményezi egy adott pozícióra.
     *
     * @param insect   A mozgatandó rovar.
     * @param location A célpozíció.
     * @return true, ha a mozgás sikerült, különben false.
     */
    public boolean move(Insect insect, Tecton location) {
        if (started && !ended && !hasCurrentTurn(insect)) return false;
        return ((Insecter) insect.getOwner()).move(insect, location);
    }

    /**
     * Egy rovar egy spórát próbál enni.
     *
     * @param insect A bogár.
     * @param spore  A spóra.
     * @return true, ha a művelet sikeres volt.
     */
    public boolean eat(Insect insect, Spore spore) {
        if (started && !ended && !hasCurrentTurn(insect)) return false;
        return ((Insecter) insect.getOwner()).eat(insect, spore);
    }

    /**
     * Megkísérli, hogy a bogár elvágjon egy gombafonalat.
     *
     * @param insect         A bogár.
     * @param mushroomThread A gombafonal.
     * @return true, ha a szeletelés sikeres.
     */
    public boolean cut(Insect insect, MushroomThread mushroomThread) {
        if (started && !ended && !hasCurrentTurn(insect)) return false;
        return ((Insecter) insect.getOwner()).cut(insect, mushroomThread);

    }

    /**
     * Új gombatest elhelyezése adott helyre.
     *
     * @param location A helyszín.
     * @return true, ha az elhelyezés sikeres.
     */
    public boolean plantMushroomStem(Tecton location) {
        if (started && !ended && turn % 2 == 0) return ((Mushroomer) getCurrentPlayer()).plantMushroomStem(location);
        return false;

    }

    public boolean throwSpore(MushroomStem mushroomStem, Tecton location) {
        if (started && !ended && !hasCurrentTurn(mushroomStem)) return false;
        return ((Mushroomer) mushroomStem.getOwner()).throwSpore(mushroomStem, location);
    }

    /**
     * Gombafonal növesztése adott pozícióba.
     *
     * @param location A célhely.
     * @return true ha a növesztés sikeres.
     */
    public boolean growThread(Tecton location) {
        if (started && !ended && turn % 2 == 0) return ((Mushroomer) getCurrentPlayer()).growMushroomThread(location);
        return false;
    }

    /**
     * Gombafonal megpróbál bekebelezni egy rovart.
     *
     * @param mushroomThread A támadó gombafonal.
     * @param insect         Az áldozat bogár.
     * @return true, ha a támadás sikeres.
     */
    public boolean eat(MushroomThread mushroomThread, Insect insect) {
        if (started && !ended && !hasCurrentTurn(mushroomThread)) return false;
        return ((Mushroomer) mushroomThread.getOwner()).eat(mushroomThread, insect);
    }

    /**
     * Megpróbálja fejleszteni a gombatörzset.
     *
     * @param mushroomStem A fejlesztendő gombatörzs.
     * @return true, ha a fejlesztés sikeres.
     */
    public boolean levelUp(MushroomStem mushroomStem) {
        if (started && !ended && !hasCurrentTurn(mushroomStem)) return false;
        return ((Mushroomer) mushroomStem.getOwner()).levelUp(mushroomStem);
    }

    public void printMap() {
        if (!started) return;
        map.printSelf();
    }

    public int getTurn() {
        if (!started) return -1;
        return turn;
    }

    public GameObject findObject(int id) {
        if (!started) return null;
        List<GameObject> gameObject = new ArrayList<>();
        gameObject.addAll(map.tectons);
        gameObject.addAll(mushroomers);
        gameObject.addAll(insecters);
        map.tectons.forEach(t -> gameObject.addAll(t.getStems()));
        map.tectons.forEach(t -> gameObject.addAll(t.getSpores()));
        map.tectons.forEach(t -> gameObject.addAll(t.getThreads()));
        map.tectons.forEach(t -> gameObject.addAll(t.getInsects()));

        for (GameObject obj : gameObject) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
    }

    /**
     * Lezárja az aktuális kört, végrehajtja a kör végén szükséges műveleteket.
     */
    @Override
    public void endRound() {
        if (!started || ended) return;
        map.endRound();
        Controller.log("[ROUND OVER]");
    }

    /**
     * Lezárja az aktuális lépést, előkészíti a következőt, és szükség esetén befejezi a játékot.
     */
    @Override
    public void endTurn() {
        if (!started || ended) return;
        Controller.log("[TURN OVER]");
        getCurrentPlayer().endTurn();
        turn++;

        if (turn % (insecters.size() + mushroomers.size()) == 0) {
            round++;
            endRound();
            Controller.log("[ROUND OVER]");
        }

        if (round == maxRound) {
            Controller.getView().showPanel("winnerView");
            ended = true;
        }

        if (!ended) Controller.log("Next turn: " + getCurrentPlayer().getName());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Game Game = (Game) o;
        return ended == Game.ended && turn == Game.turn && Objects.equals(map, Game.map) && Objects.equals(insecters, Game.insecters) && Objects.equals(mushroomers, Game.mushroomers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), map, maxRound, ended, insecters, mushroomers, turn);
    }

    @Override
    public String toString() {
        return ", maxRound=" + maxRound + ", started=" + ended + ", ended=" + ended + ", turn=" + turn + ", mushroomers=[" + mushroomers.stream().map(Player::getName).collect(Collectors.joining(", ")) + "]" + ", insecters=[" + insecters.stream().map(Player::getName).collect(Collectors.joining(", ")) + "]" + ", map=" + map;
    }
}