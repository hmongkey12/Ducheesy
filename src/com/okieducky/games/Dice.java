package com.okieducky.games;

/**
 * generates a random number, used to add to the player movement
 */
public class Dice {
    private static final int DICE_MAX = 4;

    public static int rollDice() {
        return (int) ((Math.random() * DICE_MAX) + 1);
    }
}