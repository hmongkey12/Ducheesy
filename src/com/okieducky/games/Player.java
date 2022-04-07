package com.okieducky.games;

import java.util.Arrays;
import java.util.Collection;

public class Player {
    //Fields
    private String id;
    private boolean landedOnBadSpot = false;
    private int rolledValue;
    private final String[] player = new String[21];
    private int nextSpot;
    private int previousSpot;
    private boolean win = false;
    private Collection<Integer> badSpots = Arrays.asList(3, 11, 19);
    private Collection<Integer> goodSpots = Arrays.asList(4, 8, 12, 16);

    //Constructors
    public Player(String id) {
        this.id = id;
    }

    //Methods
    public void playerStart() {
        Arrays.fill(player, Cell.REGULAR.getText());
        player[0] = getId();
    }

    //initial point and next point.
    public void playerMove() {
        int currentSpot = Arrays.asList(player).indexOf(getId());
        previousSpot = currentSpot;
        rolledValue = Dice.rollDice();
        nextSpot = currentSpot + rolledValue;
        System.out.println("Player:"+ getId() + " rolled a " + rolledValue);
        Arrays.fill(player, Cell.REGULAR.getText());
        if (nextSpot < 20) {
            if(goodSpots.contains(nextSpot)){
                System.out.println("Good Spot landed, moving 2!!");
                nextSpot = nextSpot+2;
                player[nextSpot] = getId();}
            if(badSpots.contains(nextSpot)){
                setLandedOnBadSpot(true);
                nextSpot = 0;
                previousSpot = 0;
                playerStart();
            }
            player[nextSpot] = getId();
        } else {
            win = true;
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

    public int getPreviousSpot() {
        return previousSpot;
    }

    public int getRolledValue(){
        return rolledValue;
    }

    public boolean getWin(){
        return win;
    }

    public boolean isLandedOnBadSpot() {
        return landedOnBadSpot;
    }

    public void setLandedOnBadSpot(boolean landedOnBadSpot) {
        this.landedOnBadSpot = landedOnBadSpot;
    }
}



