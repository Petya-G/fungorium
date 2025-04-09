package insect;

import mushroom.MushroomThread;
import mushroom.spore.Spore;
import tecton.Tecton;

/**
 * Az IInsect interfész egy rovar viselkedését definiálja.
 */
public interface IInsect {
    /**
     * Megpróbál elfogyasztani egy gombaspórát.
     * @param sp Elfogyasztandó spóra.
     * @return igaz, ha sikerült megenni, egyébként hamis.
     */
    boolean eat(Spore sp);

        /**
     * Megpróbál mozogni.
     * @param targetTecton A tekton, amelyre menni szeretne a rovar.
     * @return igaz, ha a mozgás sikerült, egyébként hamis
     */
    boolean move(Tecton t);

         /**
     * Megpróbál elvágni egy gombafonalat.
     * @param th Elvágandó gombafonal.
     * @return igaz, ha sikerült elvágni, egyébként hamis.
     */
    boolean cut(MushroomThread th);
}