package com.okieducky.games;

import java.util.Arrays;
import java.util.Collection;

public class Player {

    //Fields
    public String id;
    private final String[] player = new String[21];
    private int nextSpot;
    public Collection<Integer> badSpots = Arrays.asList(3, 11, 19);
    public Collection<Integer> goodSpots = Arrays.asList(4, 8, 12, 16);


    //Constructors
    public Player(String id) {
        this.id = id;
    }

    //Methods
    public void playerStart() {
        Arrays.fill(player, Cell.REGULAR.getText());
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
        Arrays.fill(player, Cell.REGULAR.getText());
        if (nextSpot < 20) {
            if(goodSpots.contains(nextSpot)){
                System.out.println("Good Spot landed, moving 2!!");
                nextSpot = nextSpot+2;
                player[nextSpot] = getId();}
            if(badSpots.contains(nextSpot)){
                System.out.println("Bad Spot landed, moving back to start!!");
                nextSpot = 0;
                playerStart();
            }
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



