package model.insect;

import java.util.List;

/**
 * Ez az interfész azokat az objektumokat írja le, amelyek rovarokat (Insect) tartalmazhatnak,
 * illetve képesek azok kezelésére (hozzáadás, eltávolítás, lekérdezés).
 */
public interface IInsect {

    /**
     * Egy rovar hozzáadása az objektumhoz.
     *
     * @param insect a hozzáadandó rovar
     * @return true, ha a hozzáadás sikeres volt; egyébként false
     */
    boolean add(Insect insect);

    /**
     * Egy rovar eltávolítása az objektumból.
     *
     * @param insect az eltávolítandó rovar
     * @return true, ha az eltávolítás sikeres volt; egyébként false
     */
    boolean remove(Insect insect);

    /**
     * A jelenleg tárolt rovarok listájának lekérdezése.
     *
     * @return a rovarokat tartalmazó lista
     */
    List<Insect> getInsects();

    /**
     * Egy adott azonosítójú rovar lekérdezése.
     * Alapértelmezett implementáció, amely az ID alapján keres a rovarlistában.
     *
     * @param id a keresett rovar azonosítója
     * @return a megfelelő rovar példány, vagy null, ha nem található
     */
    default Insect getInsect(int id) {
        return getInsects().stream()
                .filter(insect -> insect.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
