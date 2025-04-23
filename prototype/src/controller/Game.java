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
    private final Map map = new Map();
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
        turn = 0;
        //PÁLYA GENERÁLÁS, HA MÉG NINCS MEGFELELŐ PÁLYA
        return true;
    }

    public boolean addPlayers() {
        if (players.size() < 7)
        {
            players.add(new Mushroomer());
            players.add(new Insecter());
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
        return players.get(turn % players.size());
    }

    /**
     * Visszaadja a játék jelenlegi győztesét a pontszámok alapján.
     *
     * @return A legtöbb pontot elért játékos, vagy null ha nincs ilyen.
     */
    public Player getWinner() {
        return players.stream().max(Comparator.comparingInt(Player::getScore)).orElse(null);
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
     * @param mushroomer A műveletet végző gombász.
     * @param location   A helyszín.
     * @return true, ha az elhelyezés sikeres.
     */
    public boolean plantMushroomStem(Mushroomer mushroomer, Tecton location) {
        if (!hasCurrentTurn(mushroomer)) return false;
        return mushroomer.plantMushroomStem(location);
    }

    /**
     * Gombafonal növesztése adott pozícióba.
     *
     * @param mushroomer A gombász.
     * @param location   A célhely.
     * @return true ha a növesztés sikeres.
     */
    public boolean growThread(Mushroomer mushroomer, Tecton location) {
        if (!hasCurrentTurn(mushroomer)) return false;
        return mushroomer.growMushroomThread(location);
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

    public void printMap()
    {
        map.printSelf();
    }

    public void listPlayers()
    {
        for (Player player : players) {
            System.out.println(player.getName() + player.getId() + "\n");
        }
    }

    
    public void listShroomStem()
    {
        for (Player player : players) {
            // for (MushroomStem stem : player.getName().) {
                
            // }
            System.out.println(player.getName() + player.getId() + "\n");
        }
    }

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