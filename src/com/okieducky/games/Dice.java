package com.okieducky.games;

public class Dice {
    private static final int DICE_MAX = 4;

    public static int rollDice() {
        return (int) ((Math.random() * DICE_MAX) + 1);
    }
}