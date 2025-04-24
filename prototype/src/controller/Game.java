package controller;

import model.Map;
import model.core.*;
import model.insect.Insect;
import model.insect.Insecter;
import model.mushroom.MushroomStem;
import model.mushroom.MushroomThread;
import model.mushroom.Mushroomer;
import model.mushroom.spore.Spore;
import model.tecton.Tecton;

import java.io.Serializable;
import java.util.*;

/**
 * A játékmenet kezeléséért felelős osztály, amely kezeli a köröket, a játékosokat
 * és a különböző műveleteket (pl. mozgás, támadás, növekedés).
 * Irányítja a játék logikáját, valamint nyilvántartja az aktuális játékállapotot.
 */
public class Game extends Identifiable implements ITurn, IRound, Serializable {
    public static final Random random = new Random();
    /**
     * Egy körben lejátszható lépések maximális száma.
     */
    private final int maxTurn = 10;
    /**
     * A játék pályája
     */
    private Map map = new Map();
    /**
     * Azt jelzi, hogy a játék véget ért-e.
     */
    private boolean ended = false;
    /**
     * A játékban résztvevő játékosok listája.
     */
    private final List<Player> players = new ArrayList<>();
    /**
     * Az aktuális kör sorszáma.
     */
    private int turn = 0;

    /**
     * Nyilvántartja, elkezdődött-e már a játék
     */
    private boolean hasStarted = false;

    public Game() {
    }

    public Game(Game game) {
        super(game);
        this.map.tectons.addAll(game.map.tectons);
        this.players.addAll(game.players);
        this.ended = game.ended;
        this.turn = game.turn;
        this.hasStarted = game.hasStarted;
    }

    /**
     * Ellenőrzi, hogy az adott entitás gazdája az aktuális játékos-e.
     *
     * @param entity Az entitás, amelyhez az ellenőrzést végezzük.
     * @return true, ha a gazda az aktuális játékos, egyébként false.
     */
    private boolean hasCurrentTurn(Entity entity) {
        return getCurrentPlayer().equals(entity.getOwner());
    }

    /**
     * Ellenőrzi, hogy a megadott játékos-e az aktuális játékos.
     *
     * @param player A játékos, akit ellenőrzünk.
     * @return true, ha az aktuális játékos, különben false.
     */
    private boolean hasCurrentTurn(Player player) {
        return getCurrentPlayer().equals(player);
    }


    /**
     * Elindítja a játékot, a hasStarted változó beállításával
     */
    public boolean startGame() {
        if (hasStarted) return false;
        hasStarted = true;

        
        //PÁLYA GENERÁLÁS, HA MÉG NINCS MEGFELELŐ PÁLYA
        if (!map.isGenerated()) {
            map.generate(random);
        }

        return true;
    }

    public boolean addPlayer(Player player) {
        if (players.size() <= 8) {
            return players.add(player);
        }
        return false;
    }

    public boolean addPlayers() {
        if (!map.isGenerated()) {
            System.out.println("map must be generated first using /start!");
            return false;
        }
        if (players.size() <= 8) {
            players.add(new Mushroomer(map.tectons.get(random.nextInt(map.tectons.size()))));
            players.add(new Insecter(map.tectons.get(random.nextInt(map.tectons.size()))));
            return true;
        }
        return false;
    }

    /**
     * Lekérdezi az aktuális körön lévő játékost.
     *
     * @return Az aktuális játékos.
     */
    public Player getCurrentPlayer() {
        if (players.size() == 0) {
            return null;
        }
        return players.get(turn % players.size());
    }

    /**
     * Visszaadja a játék jelenlegi győzteseit, az első a gombászok közül, a második a rovarászok közül
     *
     * @return A legtöbb pontot elért játékosok, vagy null ha nincs ilyen.
     */
    public List<Player> getWinners() {
        List<Player> winners = new ArrayList<>();
        winners.add(players.stream().filter(p -> p instanceof Mushroomer).max(Comparator.comparingInt(Player::getScore)).orElse(null));
        winners.add(players.stream().filter(p -> p instanceof Insecter).max(Comparator.comparingInt(Player::getScore)).orElse(null));
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
        if (!hasCurrentTurn(insect)) return false;
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
        if (!hasCurrentTurn(insect)) return false;
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
        if (!hasCurrentTurn(insect)) return false;
        return ((Insecter) insect.getOwner()).cut(insect, mushroomThread);

    }

    /**
     * Új gombatest elhelyezése adott helyre.
     *
     * @param location A helyszín.
     * @return true, ha az elhelyezés sikeres.
     */
    public boolean plantMushroomStem(Tecton location) {
        if(turn % 2 == 0) return ((Mushroomer) getCurrentPlayer()).plantMushroomStem(location);
        return false;
        
    }

    public boolean throwSpore(MushroomStem mushroomStem, Tecton location) {
        if (!hasCurrentTurn(mushroomStem)) return false;
        return ((Mushroomer) mushroomStem.getOwner()).throwSpore(mushroomStem, location);
    }


    /**
     * Gombafonal növesztése adott pozícióba.
     *
     * @param location A célhely.
     * @return true ha a növesztés sikeres.
     */
    public boolean growThread(Tecton location) {
        if(turn % 2 == 0) return ((Mushroomer) getCurrentPlayer()).growMushroomThread(location);
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
        if (!hasCurrentTurn(mushroomThread)) return false;
        return ((Mushroomer) mushroomThread.getOwner()).eat(mushroomThread, insect);
    }

    /**
     * Megpróbálja fejleszteni a gombatörzset.
     *
     * @param mushroomStem A fejlesztendő gombatörzs.
     * @return true, ha a fejlesztés sikeres.
     */
    public boolean levelUp(MushroomStem mushroomStem) {
        if (!hasCurrentTurn(mushroomStem)) return false;
        return ((Mushroomer) mushroomStem.getOwner()).levelUp(mushroomStem);
    }

    public void printMap() {
        map.printSelf();
    }

    public void listPlayers() {
        for (Player player : players) {
            System.out.println(player.getName() + player.getId() + "\n");
        }
    }


    public void listShroomStem() {
        for (Player player : players) {
            // for (MushroomStem stem : player.getName().) {

            // }
            System.out.println(player.getName() + player.getId() + "\n");
        }
    }

    /*public Identifiable findObject(int id) {
        List<Identifiable> identifiable = new ArrayList<>();
        identifiable.addAll(map.tectons);
        identifiable.addAll(players);
        map.tectons.forEach(t -> identifiable.addAll(t.getStems()));
        map.tectons.forEach(t -> identifiable.addAll(t.getSpores()));
        map.tectons.forEach(t -> identifiable.addAll(t.getThreads()));
        map.tectons.forEach(t -> identifiable.addAll(t.getInsects()));

        return identifiable.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }*/

    /**
     * Lezárja az aktuális kört, végrehajtja a kör végén szükséges műveleteket.
     */
    @Override
    public void endRound() {
        map.endRound();
    }

    /**
     * Lezárja az aktuális lépést, előkészíti a következőt, és szükség esetén befejezi a játékot.
     */
    @Override
    public void endTurn() {
        if (players.size() == 0) {
            System.out.println("there are no players in the game!");
            return;
        }
        turn++;
        if (turn % players.size() == 0) ended = true;
        if (turn == maxTurn) ended = true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Game game = (Game) o;
        return ended == game.ended && turn == game.turn && Objects.equals(map, game.map) && Objects.equals(players, game.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), map, maxTurn, ended, players, turn);
    }
}