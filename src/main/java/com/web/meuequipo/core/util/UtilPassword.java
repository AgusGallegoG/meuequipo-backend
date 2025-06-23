package com.web.meuequipo.core.util;

import java.security.SecureRandom;

public class UtilPassword {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_";

    private static final Integer LENGTH = 12;

    private static final String ALL = UPPER + LOWER + DIGITS + SYMBOLS;

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateSecurePass() {
        StringBuilder builder = new StringBuilder(LENGTH);

        builder.append(randomChar(UPPER));
        builder.append(randomChar(LOWER));
        builder.append(randomChar(DIGITS));
        builder.append(randomChar(SYMBOLS));
        builder.append(randomChar(ALL));

        for (int i = 0; i < LENGTH; i++) {
            builder.append(randomChar(ALL));
        }

        return shuffleString(builder.toString());
    }

    private static char randomChar(String chars) {
        return chars.charAt(RANDOM.nextInt(chars.length()));
    }

    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int index = RANDOM.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[index];
            characters[index] = temp;
        }
        return new String(characters);
    }
}
