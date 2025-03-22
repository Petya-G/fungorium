package mushroom;

import java.util.*;

import mushroom.spore.*;
import core.Debug;
import core.Player;
import tecton.*;

public class Mushroomer extends Player implements ISpore, IStem, IThread {
    private List<Spore> spores;
    private List<MushroomStem> stems;
    private List<MushroomThread> threads;

    /**
     * Visszaadja a stem-ek listáját melyek a mushroomerhez tartoznak
     */
    public List<MushroomStem> getStems() {
        return stems;
    }

    /**
     * Konstruktor megadott listákkal
     */
    public Mushroomer(List<Spore> spores, List<MushroomStem> stems, List<MushroomThread> threads) {
        this.spores = spores;
        this.stems = stems;
        this.threads = threads;
    }

    /**
     * Default konstruktor
     */
    public Mushroomer() {
        this.spores = new ArrayList<>();
        this.stems = new ArrayList<>();
        this.threads = new ArrayList<>();
    }

    /**
     * A mushroomerhez tartozó thread-ek listája
     */
    public List<MushroomThread> getThreads() {
        return threads;
    }

    /**
     * Gombatest lerakása egy tektonra
     */
    public Boolean plantMushroomstem(Tecton tecton) {
        Debug.DBGFUNC("");
        MushroomStem ms = new MushroomStem(this, tecton);
        return tecton.add(ms) ? add(ms) : false;
    }

    /**
     * Gombafonal lerakása egy tektonra
     */
    public Boolean growMushroomthread(Tecton tecton) {
        Debug.DBGFUNC("");
        MushroomThread mt = new MushroomThread(this, tecton);
        return tecton.add(mt) ? add(mt) : false;
    }

    /**
     * Spóra dobása egy gombatestből egy tektonra
     */
    public Boolean throwSpore(MushroomStem ms, Tecton tecton) {
        Debug.DBGFUNC("");
        return ms.throwSpore(tecton);
    }

    /**
     * Gombatest fejlesztése
     */
    public Boolean levelUp(MushroomStem ms) {
        Debug.DBGFUNC("");
        return ms.levelUp();
    }

    /**
     * Új thread hozzárendelése a gombászhoz
     */
    @Override
    public boolean add(MushroomThread th) {
        return threads.add(th);
    }

    /**
     * thread törlése a gombásztól
     */
    @Override
    public boolean remove(MushroomThread th) {
        return threads.remove(th);
    }

    /**
     * Új stem hozzárendelése a gombászhoz
     */
    @Override
    public boolean add(MushroomStem ms) {
        Debug.DBGFUNC("");
        stems.add(ms);
        System.out.println(stems);
        return true;
    }

    /**
     * Stem törlése a gombásztól
     */
    @Override
    public boolean remove(MushroomStem ms) {
        return stems.remove(ms);
    }

    /**
     * Spóra hozzáadása a gombászhoz
     */
    @Override
    public boolean add(Spore sp) {
        return spores.add(sp);
    }

    /**
     * Spóra törlése a gombásztól
     */
    @Override
    public boolean remove(Spore sp) {
        return spores.remove(sp);
    }

    /**
     * Gombász pontszámának lekérése
     */
    @Override
    public Integer getScore() {
        return score;
    }

    /**
     * Gombász pontszámának növelése
     */
    @Override
    public void addScore(Integer score) {
        this.score += score;
    }

    /**
     * Kör vége, itt meghívjuk a hozzánk tartozó objektumok endTurn függvényeit
     */
    @Override
    public void endTurn() {
        stems.forEach(ms -> ms.endTurn());
        threads.forEach(th -> th.endTurn());
        spores.forEach(sp -> sp.endTurn());
    }
}