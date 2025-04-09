package insect;

import core.Debug;
import core.Entity;
import effect.Effect;
import tecton.Tecton;

import java.util.List;

import mushroom.MushroomThread;
import mushroom.spore.Spore;

/**
 * Egy rovart reprezentál a játékban.
 * A rovarok tudnak gombaspórákat enni, gombafonalakon átmenni, és gombafonalakat elvágni.
 * A különböző spórák befolyásolják a képességeiket.
 */
public class Insect extends Entity implements IInsect {
    protected List<Effect> effects;
    protected boolean paralyzed;
    protected boolean clawParalyzed;
    protected int baseSpeed;
    protected double speedModifier;


     /**
     * Megállapítja, hogy a rovar bénult állapotban van-e.
     * @return igaz, ha bénult, egyébként hamis
     */
    public boolean isParalyzed() {
        Debug.DBGFUNC("Bénulás lekérdezése");
        return paralyzed;
    }

     /**
     * Beállítja a rovar bénulási állapotát.
     * @param paralyzed igaz esetén megbénítja a rovart, hamis esetén visszaállítja nem bénultra.
     */
    public void setParalyzed(boolean paralyzed) {
        Debug.DBGFUNC("Bénulás beállítása");
        this.paralyzed = paralyzed;
    }


     /**
     * Megállapítja, hogy a rovar csáprágója képes-e fonalat vágni.
     * @return igaz, ha a csáprágói bénultak, egyébként hamis
     */
    public boolean isClawParalyzed() {
        Debug.DBGFUNC("Csáprágó bénulás lekérdezése");
        return clawParalyzed;
    }

    /**
     * Beállítja a karom bénulás állapotát.
     * @param clawParalyzed igaz esetén megbénítja a csáprágókat, hamis esetén visszaállítja nem bénultra.
     */
    public void setClawParalyzed(boolean clawParalyzed) {
        Debug.DBGFUNC("Csáprágó bénulás beállítása");
        this.clawParalyzed = clawParalyzed;
    }

    /**
     * Visszaadja a rovar módisítók nélküli mozgási sebességét.
     * @return alapsebesség, módosítók nélkül
     */
    public int getBaseSpeed() {
        Debug.DBGFUNC("Alapsebesség lekérdezése");
        return baseSpeed;
    }

    /**
     * Beállítja a módisítók nélküli mozgási sebességet.
     * @param baseSpeed új alapsebesség érték
     */
    public void setBaseSpeed(int baseSpeed) {
        Debug.DBGFUNC("Alapsebesség beállítása");
        this.baseSpeed = baseSpeed;
    }

    /**
     * Lekéri a sebességmódosítót.
     * @return visszaadja a sebességmódosítót
     */
    public double getSpeedModifier() {
        Debug.DBGFUNC("Sebességmódosító lekérdezése");
        return speedModifier;
    }

     /**
     * Beállítja a sebességmódosítót.
     * @param speedModifier sebességmódosító
     */
    public void setSpeedModifier(double speedModifier) {
        Debug.DBGFUNC("Sebességmódosító beállítása");
        this.speedModifier = speedModifier;
    }

    /**
     * Eltávolít egy hatást a rovarról.
     * @param e eltávolítandó hatás
     */
    public void remove(Effect e) {
        Debug.DBGFUNC("Effekt eltávolítása a rovarról");
        effects.remove(e);
    }

    /**
     * Új hatást ad a rovarhoz.
     * @param e  hatás, amit hozzáadunk a rovarhoz
     */
    public void add(Effect e) {
        Debug.DBGFUNC("Effekt hozzáadása a rovarhoz");
        effects.add(e);
    }

     /**
     * Megpróbál elfogyasztani egy gombaspórát.
     * @param sp Elfogyasztandó spóra.
     * @return igaz, ha sikerült megenni, egyébként hamis.
     */
    @Override
    public boolean eat(Spore sp) {
        Debug.DBGFUNC("Spora megevése");
        if (paralyzed) {
            return false;
        }
        add(sp.getEffect());
        sp.remove();
        return true;
    }

    /**
     * Megpróbál mozogni.
     * @param targetTecton A tekton, amelyre menni szeretne a rovar.
     * @return igaz, ha a mozgás sikerült, egyébként hamis
     */
    @Override
    public boolean move(Tecton targetTecton) {
        Debug.DBGFUNC("Rovar mozog");
        if(paralyzed) return false;

        Tecton baseTecton = getLocation(); 

        baseTecton.removeInsect(this);
        targetTecton.addInsect(this);

        setLocation(targetTecton);
        return true;
    }

     /**
     * Megpróbál elvágni egy gombafonalat.
     * @param th Elvágandó gombafonal.
     * @return igaz, ha sikerült elvágni, egyébként hamis.
     */
    @Override
    public boolean cut(MushroomThread th) {
        Debug.DBGFUNC("Gombafonal elvágása");
        if(clawParalyzed || paralyzed) return false;

        Tecton baseTecton = getLocation(); 
        return baseTecton.remove(th);

    }

     /**
     * Végrehajtja a kör végén szükséges folyamatokat.
     * Csökkenti a rovaron lévő hatások idejét, szükség esetén törli is őket.
     * Ezután újra kiértékeli az összesített hatást.
     */
    @Override
    public void endTurn() {
        Debug.DBGFUNC("Kör vége");

        for(Effect e : effects){
            if(e.getDuration()==1){
                remove(e);
            }else{
                e.decreaseDuration();
            }
        }

        paralyzed=false;
        clawParalyzed=false;
        speedModifier=baseSpeed;

        for(Effect e : effects){
            e.apply(this);
        }
    }

    /**
     * Eltávolítja a rovart.
     */
    @Override
    public void remove() {
        Debug.DBGFUNC("Rovar eltávolítva");
    }
}