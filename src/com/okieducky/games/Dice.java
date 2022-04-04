package com.okieducky.games;

public class Dice {
    private static final int DICE_MAX = 3;
    private static int diceCount = 1;


    public static int rollDice() {
        int roll = 0;
        for (int i = 0; i < diceCount; i++) {
            roll = (int) ((Math.random() * DICE_MAX) + 1);
        }
        return roll;
    }
}