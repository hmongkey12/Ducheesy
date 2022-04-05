package com.okieducky.games;

import java.util.Arrays;

public class Player {

    //Fields
    public String id;
    private String[] player = new String[21];
    private int nextSpot;



    private int currentSpot;

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
        nextSpot = currentSpot + Dice.rollDice();
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
    public void setNextSpot(int nextSpot) {
        this.nextSpot = nextSpot;
    }
    public int getNextSpot() {
        return nextSpot;
    }
    public void setCurrentSpot(int currentSpot) {
        this.currentSpot = currentSpot;
    }
}



