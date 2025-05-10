package view;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {
    private static final Map<String, ImageIcon> icons = new HashMap<>();
    private static final String BASE_PATH = "src/resources/icons/";

    static {
        loadPlayerImages();
        loadMushroomImages();
        loadSporeImages();
        loadTectonImages();
        System.out.println(System.getProperty("user.dir"));
    }

    private static void loadPlayerImages() {
        for (int i = 1; i <= 4; i++) {
            icons.put("insect_normal_" + i, loadIcon(BASE_PATH + "insect/normal/insect_normal_" + i + ".png"));
            icons.put("insect_paralyzed_" + i, loadIcon(BASE_PATH + "insect/paralyzed/insect_paralyzed_" + i + ".png"));
        }
    }

    private static void loadMushroomImages() {
        for (int i = 1; i <= 4; i++) {
            icons.put("mushroom_stem_" + i, loadIcon(BASE_PATH + "mushroom/stem/mushroom_stem_" + i + ".png"));
            icons.put("mushroom_thread_" + i, loadIcon(BASE_PATH + "mushroom/thread/mushroom_thread_" + i + ".png"));
        }
    }

    private static void loadSporeImages() {
        String[] sporeTypes = {"clawparalyzing", "paralyzing", "slowing", "speeding", "splitting"};
        for (String type : sporeTypes) {
            for (int i = 1; i <= 4; i++) {
                icons.put("spore_" + type + "_" + i, loadIcon(BASE_PATH + "spore/" + type + "/spore_" + type + "_" + i + ".png"));
            }
        }
    }

    private static void loadTectonImages() {
        icons.put("tecton_basic", loadIcon(BASE_PATH + "tecton/tecton_basic.png"));
        icons.put("tecton_lifesupport", loadIcon(BASE_PATH + "tecton/tecton_lifesupport.png"));
        icons.put("tecton_singlethreaded", loadIcon(BASE_PATH + "tecton/tecton_singlethreaded.png"));
        icons.put("tecton_stemless", loadIcon(BASE_PATH + "tecton/tecton_stemless.png"));
        icons.put("tecton_threadconsuming", loadIcon(BASE_PATH + "tecton/tecton_threadconsuming.png"));
    }

    private static ImageIcon loadIcon(String path) {
        try {
            return new ImageIcon(ImageManager.class.getResource(path));
        } catch (Exception e) {
            System.err.println("Image not found: " + path);
            return null;
        }
    }

    public static ImageIcon getIcon(String key) {
        return icons.get(key);
    }
}
