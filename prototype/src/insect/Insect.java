package insect;

import core.Debug;
import core.Entity;
import effect.Effect;
import tecton.Tecton;

import java.util.ArrayList;
import java.util.List;

import mushroom.MushroomThread;
import mushroom.spore.Spore;

/**
 * Egy rovart reprezentál a játékban.
 * A rovarok tudnak gombaspórákat enni, gombafonalakon átmenni, és
 * gombafonalakat elvágni.
 * A különböző spórák befolyásolják a képességeiket.
 */
public class Insect extends Entity {
    protected List<Effect> effects;
    protected boolean paralyzed;
    protected boolean clawParalyzed;
    protected int baseSpeed;
    protected double speedModifier;

    public Insect(Insecter owner, Tecton location) {
        this.owner = owner;
        this.location = location;
        effects = new ArrayList<>();
        paralyzed = false;
        clawParalyzed = false;
        baseSpeed = 1;
        speedModifier = 1.0;
    }

    /**
     * Megállapítja, hogy a rovar bénult állapotban van-e.
     *
     * @return igaz, ha bénult, egyébként hamis
     */
    public boolean isParalyzed() {
        Debug.DBGFUNC("Bénulás lekérdezése");
        return paralyzed;
    }

    /**
     * Beállítja a rovar bénulási állapotát.
     *
     * @param paralyzed igaz esetén megbénítja a rovart, hamis esetén visszaállítja
     *                  nem bénultra.
     */
    public void setParalyzed(boolean paralyzed) {
        Debug.DBGFUNC("Bénulás beállítása");
        this.paralyzed = paralyzed;
    }

    /**
     * Megállapítja, hogy a rovar csáprágója képes-e fonalat vágni.
     *
     * @return igaz, ha a csáprágói bénultak, egyébként hamis
     */
    public boolean isClawParalyzed() {
        Debug.DBGFUNC("Csáprágó bénulás lekérdezése");
        return clawParalyzed;
    }

    /**
     * Beállítja a karom bénulás állapotát.
     *
     * @param clawParalyzed igaz esetén megbénítja a csáprágókat, hamis esetén
     *                      visszaállítja nem bénultra.
     */
    public void setClawParalyzed(boolean clawParalyzed) {
        Debug.DBGFUNC("Csáprágó bénulás beállítása");
        this.clawParalyzed = clawParalyzed;
    }

    /**
     * Visszaadja a rovar módisítók nélküli mozgási sebességét.
     *
     * @return alapsebesség, módosítók nélkül
     */
    public int getBaseSpeed() {
        Debug.DBGFUNC("Alapsebesség lekérdezése");
        return baseSpeed;
    }

    /**
     * Lekéri a sebességmódosítót.
     *
     * @return visszaadja a sebességmódosítót
     */
    public double getSpeedModifier() {
        Debug.DBGFUNC("Sebességmódosító lekérdezése");
        return speedModifier;
    }

    /**
     * Beállítja a sebességmódosítót.
     *
     * @param speedModifier sebességmódosító
     */
    public void setSpeedModifier(double speedModifier) {
        Debug.DBGFUNC("Sebességmódosító beállítása");
        this.speedModifier = speedModifier;
    }

    /**
     * Új hatást ad a rovarhoz.
     *
     * @param e hatás, amit hozzáadunk a rovarhoz
     */
    public void add(Effect e) {
        Debug.DBGFUNC("Effekt hozzáadása a rovarhoz");
        effects.add(e);
        e.apply(this);
    }

    /**
     * Eltávolít egy hatást a rovarról.
     *
     * @param e eltávolítandó hatás
     */
    public void remove(Effect e) {
        Debug.DBGFUNC("Effekt eltávolítása a rovarról");
        effects.remove(e);
        e.remove(this);
    }

    /**
     * Megpróbál elfogyasztani egy gombaspórát.
     *
     * @param sp Elfogyasztandó spóra.
     * @return igaz, ha sikerült megenni, egyébként hamis.
     */
    public boolean eat(Spore sp) {
        Debug.DBGFUNC("Spora megevése");
        if (paralyzed || clawParalyzed)
            return false;

        add(sp.getEffect());
        sp.remove();
        return true;
    }

    /**
     * Megpróbál mozogni.
     *
     * @param targetTecton A tekton, amelyre menni szeretne a rovar.
     * @return igaz, ha a mozgás sikerült, egyébként hamis
     */
    public boolean move(Tecton targetTecton) {
        //TODO check if any player has thread connection betwen the insect location and the target location
        //TODO somwhere take into account speedModifier
        Debug.DBGFUNC("Rovar mozog");
        if (paralyzed)
            return false;

        setLocation(targetTecton);
        return true;
    }

    /**
     * Megpróbál elvágni egy gombafonalat.
     *
     * @param th Elvágandó gombafonal.
     * @return igaz, ha sikerült elvágni, egyébként hamis.
     */
    public boolean cut(MushroomThread th) {
        Debug.DBGFUNC("Gombafonal elvágása");
        if (clawParalyzed || paralyzed)
            return false;

        th.setCutoff(true);
        return true;
    }
    
    public void split() {
        Insect insect = new Insect((Insecter) getOwner(), getLocation());
        ((Insecter) getOwner()).add(insect);
        getLocation().add(insect);
    }

    /**
     * Beállítja a rovar helyét
     *
     * @param location A tekton, amin a rovar van
     */
    @Override
    public void setLocation(Tecton location) {
        this.location.remove(this);
        location.add(this);
        super.setLocation(location);}
    
    /**
     * Végrehajtja a kör végén szükséges folyamatokat.
     * Törli a lejáró hatásokat
     * Ezután újra kiértékeli az összesített hatást.
     */
    @Override
    public void endTurn() {
        Debug.DBGFUNC("Kör vége");
        List<Effect> endingEffects = effects.stream().filter(e -> e.getDuration() == 1).toList();
        endingEffects.forEach(e -> e.remove(this));
        effects.removeAll(endingEffects);

        effects.forEach(e -> e.apply(this));
    }

    /**
     * Eltávolítja a rovart.
     */
    @Override
    public void remove() {
        getLocation().remove(this);
        ((Insecter) getOwner()).remove(this);
    }
}