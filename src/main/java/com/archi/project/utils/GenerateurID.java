package com.archi.project.utils;

import java.util.Random;

public class GenerateurID {
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 8;
    private static final Random RANDOM = new Random();

    public static String generateShortId() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            int index = RANDOM.nextInt(CHARS.length());
            sb.append(CHARS.charAt(index));
        }
        return sb.toString();

    }
}
