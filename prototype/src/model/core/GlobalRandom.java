package model.core;

import java.util.Random;

public class GlobalRandom {
    private static final Random random = new Random();

    private GlobalRandom() {
    }

    public static Random getInstance() {
        return random;
    }
}