package view;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * Az {@code ImageManager} egy statikus segédosztály, amely az alkalmazás összes képének betöltéséért és eléréséért felel.
 *
 * <p>A képek kulcs-érték párokként tárolódnak egy {@link Map}-ben, így később egyszerűen lekérhetők a {@code getIcon()} metódussal.</p>
 *
 * <p>Támogatott képtípusok:</p>
 * <ul>
 *     <li>Rovarok (insect)</li>
 *     <li>Gombatörzsek és szálak (mushroom)</li>
 *     <li>Spórák (spore)</li>
 *     <li>Tecton típusok</li>
 * </ul>
 */
public class ImageManager {

    /** Az ikonokat tároló kulcs-érték páros map. */
    private static final Map<String, ImageIcon> icons = new HashMap<>();

    /** Az alapértelmezett fájlrendszerbeli képútvonal. */
    private static String BASE_PATH = "src/resources/icons/";

    // Statikus inicializálás: összes kép betöltése induláskor
    static {
        // VSCode kompatibilitási javítás – ha nem találja a mappát, alternatív útvonalat próbál
        if (!(new File(BASE_PATH).exists())) {
            BASE_PATH = "fungorium/" + BASE_PATH;
        }

        loadInsectImages();
        loadMushroomImages();
        loadSporeImages();
        loadTectonImages();

        System.out.println(System.getProperty("user.dir")); // Naplózás: aktuális könyvtár
    }

    /**
     * Rovarikonok betöltése (normál és bénult állapotban).
     */
    private static void loadInsectImages() {
        for (int i = 1; i <= 4; i++) {
            icons.put("insect_normal_" + i, loadIcon(BASE_PATH + "insect/normal/insect_normal_" + i + ".png"));
            icons.put("insect_paralyzed_" + i, loadIcon(BASE_PATH + "insect/paralyzed/insect_paralyzed_" + i + ".png"));
        }
    }

    /**
     * Gombatörzsek és szálak ikonok betöltése.
     */
    private static void loadMushroomImages() {
        for (int i = 1; i <= 4; i++) {
            icons.put("mushroom_stem_" + i, loadIcon(BASE_PATH + "mushroom/stem/mushroom_stem_" + i + ".png"));
            icons.put("mushroom_thread_" + i, loadIcon(BASE_PATH + "mushroom/thread/mushroom_thread_" + i + ".png"));
        }
    }

    /**
     * Spóra ikonok betöltése az összes típusra és játékos színre.
     */
    private static void loadSporeImages() {
        String[] sporeTypes = {"clawparalyzing", "paralyzing", "slowing", "speeding", "splitter"};
        for (String type : sporeTypes) {
            for (int i = 1; i <= 4; i++) {
                icons.put("spore_" + type + "_" + i,
                        loadIcon(BASE_PATH + "spore/" + type + "/spore_" + type + "_" + i + ".png"));
            }
        }
    }

    /**
     * Tecton típusú objektumok ikonjainek betöltése.
     */
    private static void loadTectonImages() {
        icons.put("tecton_basic", loadIcon(BASE_PATH + "tecton/tecton_basic.png"));
        icons.put("tecton_lifesupport", loadIcon(BASE_PATH + "tecton/tecton_lifesupport.png"));
        icons.put("tecton_singlethreaded", loadIcon(BASE_PATH + "tecton/tecton_singlethreaded.png"));
        icons.put("tecton_stemless", loadIcon(BASE_PATH + "tecton/tecton_stemless.png"));
        icons.put("tecton_threadconsuming", loadIcon(BASE_PATH + "tecton/tecton_threadconsuming.png"));
    }

    /**
     * Betölt egy képet a fájlrendszerből, és ikonként visszaadja.
     *
     * @param path a fájl elérési útja
     * @return a betöltött {@code ImageIcon}, vagy {@code null} ha nem található
     */
    private static ImageIcon loadIcon(String path) {
        try {
            return new ImageIcon(path);
        } catch (Exception e) {
            System.err.println("Image not found: " + path);
            return null;
        }
    }

    /**
     * Visszaadja a megadott kulcshoz tartozó képet (ha létezik).
     *
     * @param key a regisztrált ikon kulcsa (pl. "mushroom_stem_1")
     * @return az ikon, vagy {@code null} ha nincs ilyen kulcs
     */
    public static ImageIcon getIcon(String key) {
        return icons.get(key);
    }
}
