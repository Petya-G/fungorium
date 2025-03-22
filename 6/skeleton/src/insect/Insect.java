package insect;

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
        return paralyzed;
    }

     /**
     * Beállítja a rovar bénulási állapotát.
     * @param paralyzed igaz esetén megbénítja a rovart, hamis esetén visszaállítja nem bénultra.
     */
    public void setParalyzed(boolean paralyzed) {
        this.paralyzed = paralyzed;
    }


     /**
     * Megállapítja, hogy a rovar csáprágója képes-e fonalat vágni.
     * @return igaz, ha a csáprágói bénultak, egyébként hamis
     */
    public boolean isClawParalyzed() {
        return clawParalyzed;
    }

    /**
     * Beállítja a karom bénulás állapotát.
     * @param clawParalyzed igaz esetén megbénítja a csáprágókat, hamis esetén visszaállítja nem bénultra.
     */
    public void setClawParalyzed(boolean clawParalyzed) {
        this.clawParalyzed = clawParalyzed;
    }

    /**
     * Visszaadja a rovar módisítók nélküli mozgási sebességét.
     * @return alapsebesség, módosítók nélkül
     */
    public int getBaseSpeed() {
        return baseSpeed;
    }

    /**
     * Beállítja a módisítók nélküli mozgási sebességet.
     * @param baseSpeed új alapsebesség érték
     */
    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    /**
     * Lekéri a sebességmódosítót.
     * @return visszaadja a sebességmódosítót
     */
    public double getSpeedModifier() {
        return speedModifier;
    }

     /**
     * Beállítja a sebességmódosítót.
     * @param speedModifier sebességmódosító
     */
    public void setSpeedModifier(double speedModifier) {
        this.speedModifier = speedModifier;
    }

    /**
     * Eltávolít egy hatást a rovarról.
     * @param e eltávolítandó hatás
     */
    public void remove(Effect e) {
        effects.remove(e);
    }

    /**
     * Új hatást ad a rovarhoz.
     * @param e  hatás, amit hozzáadunk a rovarhoz
     */
    public void add(Effect e) {
        effects.add(e);
    }

     /**
     * Megpróbál elfogyasztani egy gombaspórát.
     * @param sp Elfogyasztandó spóra.
     * @return igaz, ha sikerült megenni, egyébként hamis.
     */
    @Override
    public boolean eat(Spore sp) {
        // TODO Auto-generated method stub: Implement method 'eat' 
        throw new UnsupportedOperationException("Unimplemented method 'eat'");
    }

    /**
     * Megpróbál mozogni.
     * @param t A tekton, amelyre menni szeretne a rovar.
     * @return igaz, ha a mozgás sikerült, egyébként hamis
     */
    @Override
    public boolean move(Tecton t) {
        // TODO Auto-generated method stub: Implement method 'move' 
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

     /**
     * Megpróbál elvágni egy gombafonalat.
     * @param th Elvágandó gombafonal.
     * @return igaz, ha sikerült elvágni, egyébként hamis.
     */
    @Override
    public boolean cut(MushroomThread th) {
        // TODO Auto-generated method stub: Implement method 'eat' 
        throw new UnsupportedOperationException("Unimplemented method 'cut'");
    }

     /**
     * Végrehajtja a kör végén szükséges folyamatokat.
     */
    @Override
    public void endTurn() {
        // TODO Auto-generated method stub: Implement method 'endturn' 
        throw new UnsupportedOperationException("Unimplemented method 'endTurn'");
    }

    /**
     * Eltávolítja a rovart.
     */
    @Override
    public void remove() {
        // TODO Auto-generated method stub: Implement method 'remove' 
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
}