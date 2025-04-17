package model.insect;

import model.core.Entity;
import model.core.Player;
import model.effect.Effect;
import model.mushroom.MushroomThread;
import model.mushroom.spore.Spore;
import model.tecton.Tecton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;


/**
 * Egy rovart reprezentál a játékban.
 * A rovarok tudnak gombaspórákat enni, gombafonalakon átmenni, és
 * gombafonalakat elvágni.
 * A különböző spórák befolyásolják a képességeiket.
 */
public class Insect extends Entity {
    protected List<Effect> effects = new ArrayList<>();
    protected boolean paralyzed = false;
    protected boolean clawParalyzed = false;
    protected int baseSpeed = 1;
    protected double speedModifier = 1.0;

    public Insect(Insecter owner, Tecton location) {
        super(location, owner);
    }

    public Insect(Insecter owner, Tecton location, int id) {
        super(location, owner, id);
    }

    /**
     * Megállapítja, hogy a rovar bénult állapotban van-e.
     *
     * @return igaz, ha bénult, egyébként hamis
     */
    public boolean isParalyzed() {
        return paralyzed;
    }

    /**
     * Beállítja a rovar bénulási állapotát.
     *
     * @param paralyzed igaz esetén megbénítja a rovart, hamis esetén visszaállítja
     *                  nem bénultra.
     */
    public void setParalyzed(boolean paralyzed) {
        this.paralyzed = paralyzed;
    }

    /**
     * Megállapítja, hogy a rovar csáprágója képes-e fonalat vágni.
     *
     * @return igaz, ha a csáprágói bénultak, egyébként hamis
     */
    public boolean isClawParalyzed() {
        return clawParalyzed;
    }

    /**
     * Beállítja a karom bénulás állapotát.
     *
     * @param clawParalyzed igaz esetén megbénítja a csáprágókat, hamis esetén
     *                      visszaállítja nem bénultra.
     */
    public void setClawParalyzed(boolean clawParalyzed) {
        this.clawParalyzed = clawParalyzed;
    }

    /**
     * Visszaadja a rovar módisítók nélküli mozgási sebességét.
     *
     * @return alapsebesség, módosítók nélkül
     */
    public int getBaseSpeed() {
        return baseSpeed;
    }

    /**
     * Lekéri a sebességmódosítót.
     *
     * @return visszaadja a sebességmódosítót
     */
    public double getSpeedModifier() {
        return speedModifier;
    }

    /**
     * Beállítja a sebességmódosítót.
     *
     * @param speedModifier sebességmódosító
     */
    public void setSpeedModifier(double speedModifier) {
        this.speedModifier = speedModifier;
    }

    /**
     * Új hatást ad a rovarhoz.
     *
     * @param e hatás, amit hozzáadunk a rovarhoz
     */
    public void add(Effect e) {
        effects.add(e);
        e.apply(this);
    }

    /**
     * Eltávolít egy hatást a rovarról.
     *
     * @param e eltávolítandó hatás
     */
    public void remove(Effect e) {
        effects.remove(e);
    }

    /**
     * Megpróbál elfogyasztani egy gombaspórát.
     *
     * @param sp Elfogyasztandó spóra.
     * @return igaz, ha sikerült megenni, egyébként hamis.
     */
    public boolean eat(Spore sp) {
        if (paralyzed || clawParalyzed)
            return false;

        add(sp.getEffect());
        getOwner().addScore(sp.getNutrition());
        sp.remove();
        return true;
    }

    /**
     * Megvizsgálja, hogy van-e a megadott {@code Tecton}-on legalább egy érvényes {@code MushroomThread} és az ownere megegyezik.
     *
     * @param tecton A vizsgált {@code Tecton}.
     * @return {@code true}, ha van rajta legalább egy nem "eaten" gombafonál, és az ownere megegyezik, különben {@code false}.
     */
    private boolean hasValidThread(Tecton tecton,Player owner) {
        for (MushroomThread thread : tecton.getThreads()) {
            if (!thread.hasEaten() && thread.getOwner() == owner) {
                return true;
            }
        }
        return false;
    }

    /**
     * Meghatározza a távolságot az aktuális {@code Tecton} és a megadott cél {@code Tecton} között,
     * kizárólag olyan útvonalakon, amelyeken van legalább egy aktív {@code MushroomThread}.
     * 
     * A távolság azt jelenti, hogy hány lépésben (szomszédokon keresztül) lehet eljutni a célpontig,
     * csak érvényes átjárható Tectonokon keresztül.
     *
     * @param target A cél {@code Tecton}, amelynek a távolságát szeretnénk meghatározni.
     * @return A legrövidebb lépések száma az aktuális {@code Tecton}-tól a célhoz,
     *         vagy {@code -1}, ha a cél nem elérhető.
     */
    
    public int getDistanceTo(Tecton target) {
        Set<Tecton> visited = new HashSet<>();
        Queue<Tecton> queue = new LinkedList<>();
        Map<Tecton, Integer> distance = new HashMap<>();

        queue.add(getLocation());
        distance.put(getLocation(), 0);
        visited.add(getLocation());

        while (!queue.isEmpty()) {
            Tecton current = queue.poll();
            int currentDistance = distance.get(current);

            if (current == target) {
                return currentDistance;
            }

            for (Tecton neighbor : current.getNeighbours()) {
                for (MushroomThread thread : neighbor.getThreads()) {             
                    if (!visited.contains(neighbor) && hasValidThread(neighbor,thread.getOwner())) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                        distance.put(neighbor, currentDistance + 1);
                    }
                }
            }
        }

        return -1; 
    }

    /**
     * Megpróbál mozogni.
     *
     * @param targetTecton A tekton, amelyre menni szeretne a rovar.
     * @return igaz, ha a mozgás sikerült, egyébként hamis
     */
    public boolean move(Tecton targetTecton) {
        int distance = getDistanceTo(targetTecton);
        
        if (paralyzed)
            return false;

        if(distance == -1)
            return false;
        
        if(distance < baseSpeed*speedModifier)
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
        getLocation().remove(this);
        location.add(this);
        super.setLocation(location);}
    
    /**
     * Végrehajtja a kör végén szükséges folyamatokat.
     * Törli a lejáró hatásokat
     * Ezután újra kiértékeli az összesített hatást.
     */
    @Override
    public void endTurn() {
        List<Effect> endingEffects = effects.stream().filter(e -> e.getDuration() == 1).toList();
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Insect insect = (Insect) o;
        return paralyzed == insect.paralyzed && clawParalyzed == insect.clawParalyzed && baseSpeed == insect.baseSpeed && Double.compare(speedModifier, insect.speedModifier) == 0 && Objects.equals(effects, insect.effects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), effects, paralyzed, clawParalyzed, baseSpeed, speedModifier);
    }
}