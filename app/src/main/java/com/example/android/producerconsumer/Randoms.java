package com.example.android.producerconsumer;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.util.Random;

public abstract class Randoms {

    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static Random random = new Random();

    public static
    @NonNull
    String getString(@IntRange(from = 1) int stringLength) {
        StringBuilder stringBuilder = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; ++i) {
            int characterIndex = random.nextInt(CHARACTERS.length());
            char character = CHARACTERS.charAt(characterIndex);
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    public static int getInteger(@IntRange(from = 0) int minValue,
                                 @IntRange(from = 2) int maxValue) {
        return random.nextInt((maxValue - minValue) + 1) + minValue;
    }

}
