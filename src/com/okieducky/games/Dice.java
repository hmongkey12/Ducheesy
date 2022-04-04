package com.okieducky.games;

public class Dice {
    private static final int DICE_MAX = 6;
    private int diceCount;

    //constructor
    public Dice(){}
    public Dice(int diceCount){
        this.diceCount = diceCount;
    }

    //business logic
    public int rollDice(){
        int roll = 0;
        for(int i = 0; i < diceCount; i++){
            roll = (int) ((Math.random() * DICE_MAX) + 1);
        }
        return roll;
    }
}