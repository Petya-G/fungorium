package model.insect;

import controller.visitor.GameObjectVisitor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import model.core.Player;
import model.mushroom.MushroomThread;
import model.mushroom.spore.Spore;
import model.tecton.Tecton;

/**
 * Az {@code Insecter} osztály a játékos egy speciális típusa, aki rovarokat (Insect) irányít.
 *
 * Ez az osztály az {@code IInsect} interfészt is implementálja, lehetővé téve a rovarok
 * kezelését, például mozgásukat, spórák elfogyasztását és gombafonalak elvágását.
 */
public class Insecter extends Player implements IInsect, Serializable {

    /** A rovarokat tartalmazó lista, amelyeket ez a játékos irányít. */
    private List<Insect> insects = new ArrayList<>();

    /** Statikus számláló az egyedi azonosító kiosztáshoz. */
    static int IDCounter = 0;

    /** Az adott Insecter egyedi azonosítója. */
    private final int insecterID;

    /**
     * Alapértelmezett konstruktor, amely új azonosítót generál.
     */
    public Insecter() {
        insecterID = IDCounter++;
    }

    /**
     * Konstruktor, amely inicializál egy rovart egy adott helyre.
     *
     * @param location a kezdőhely (Tecton), ahova a rovar kerül
     */
    public Insecter(Tecton location) {
        insecterID = IDCounter++;
        createInsect(location);
    }

    /**
     * Konstruktor, amely explicit játékos azonosítót és kezdőhelyet is megad.
     *
     * @param location a rovar kezdőhelye
     * @param id a játékos azonosítója
     */
    public Insecter(Tecton location, int id) {
        super(id);
        insecterID = IDCounter++;
        createInsect(location);
    }

    /**
     * Másoló konstruktor, amely átveszi egy másik {@code Insecter} rovarkészletét.
     *
     * @param insecter a másolandó Insecter példány
     */
    public Insecter(Insecter insecter) {
        super(insecter);
        insecterID = IDCounter++;
        insects.addAll(insecter.insects);
    }

    /**
     * Visszaadja az Insecter egyedi azonosítóját.
     *
     * @return az azonosító
     */
    public int getInsecterID() {
        return insecterID;
    }

    /**
     * Egy rovarral elfogyaszt egy spórát.
     *
     * @param insect a rovar, amely végrehajtja a műveletet
     * @param sp a spóra, amit elfogyaszt
     * @return true, ha a művelet sikeres volt
     */
    public boolean eat(Insect insect, Spore sp) {
        if (insect.eat(sp)) {
            addScore(1);
            return true;
        }
        return false;
    }

    /**
     * Egy rovar áthelyezése másik tectonra.
     *
     * @param insect a mozgatandó rovar
     * @param t a cél tecton
     * @return true, ha a mozgás sikeres volt
     */
    public boolean move(Insect insect, Tecton t) {
        return insect.move(t);
    }

    /**
     * Egy rovarral elvág egy gombafonalat.
     *
     * @param insect a műveletet végző rovar
     * @param th az elvágandó fonal
     * @return true, ha a művelet sikeres volt
     */
    public boolean cut(Insect insect, MushroomThread th) {
        if (insect.cut(th)) {
            addScore(1);
            return true;
        }
        return false;
    }

    /**
     * Új rovar létrehozása és elhelyezése egy adott tectonra.
     *
     * @param location a rovar helye
     * @return true, ha a létrehozás sikeres volt
     */
    public boolean createInsect(Tecton location) {
        Insect i = new Insect(this, location);
        location.add(i);
        return add(i);
    }

    /**
     * A kör végén végrehajtandó műveletek, például rovarok állapotfrissítése.
     */
    @Override
    public void endTurn() {
        List<Insect> insects_copy = new ArrayList<>(insects);
        insects_copy.forEach(Insect::endTurn);
    }

    /**
     * Egy rovar hozzáadása a listához.
     *
     * @param insect a hozzáadandó rovar
     * @return true, ha a hozzáadás sikeres
     */
    @Override
    public boolean add(Insect insect) {
        return insects.add(insect);
    }

    /**
     * Egy rovar eltávolítása a listából.
     *
     * @param insect az eltávolítandó rovar
     * @return true, ha az eltávolítás sikeres
     */
    @Override
    public boolean remove(Insect insect) {
        return insects.remove(insect);
    }

    /**
     * A rovarokat tartalmazó lista lekérdezése.
     *
     * @return az Insect példányokat tartalmazó lista
     */
    @Override
    public List<Insect> getInsects() {
        return insects;
    }

    /**
     * A látogatói minta fogadása – jelenleg nem csinál semmit.
     *
     * @param visitor a látogató
     */
    @Override
    public void accept(GameObjectVisitor visitor) {
        // Üres, mivel az Insecter jelenleg nem része a visitálható struktúrának
    }

    /**
     * Két Insecter példány egyenlőségének ellenőrzése.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Insecter insecter = (Insecter) o;
        return Objects.equals(insects, insecter.insects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), insects);
    }

    /**
     * Az objektum szöveges reprezentációja (debughoz hasznos).
     *
     * @return az Insecter szöveges formája, beleértve az általa irányított rovarokat
     */
    @Override
    public String toString() {
        return super.toString() + " "
                + "insects=[" + insects.stream().map(Insect::getName).collect(Collectors.joining(", ")) + "]";
    }
}
