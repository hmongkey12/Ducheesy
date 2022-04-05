package com.okieducky.games;

import java.util.Arrays;

public class Player {

    //Fields
    public String id;
    private String[] player = new String[21];
    private int nextSpot;


    //Constructors
    public Player(String id) {
        this.id = id;
    }

    //Methods
    public void playerStart() {
        Arrays.fill(player, "⬜️");
        player[0] = getId();
    }

    public void win() {
        System.out.println("The winner is PLAYER: " + getId());
    }




    //initial point and next point.
    public void playerMove() {
        int currentSpot = Arrays.asList(player).indexOf(getId());
        int diceRoll = Dice.rollDice();
        nextSpot = currentSpot + diceRoll;
        System.out.println("Player:"+ getId() + " rolled a " + diceRoll);
        Arrays.fill(player, "⬜️");
        if (nextSpot < 20) {
            player[nextSpot] = getId();
        } else {
            win();
        }
    }

    public void updateSpot(int moveBy) {
        int currentSpot = Arrays.asList(player).indexOf(getId());
        nextSpot = currentSpot + moveBy;
        Arrays.fill(player, "⬜️");
        if (nextSpot < 20) {
            player[nextSpot] = getId();
        } else {
            win();
        }
    }


    //getters
    public String[] getPlayer() {
        return player;
    }

    public String getId() {
        return id;
    }
    public int getNextSpot() {
        return nextSpot;
    }

}



