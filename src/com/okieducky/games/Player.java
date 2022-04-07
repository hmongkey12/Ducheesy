package com.okieducky.games;

import java.util.Arrays;
import java.util.Collection;

public class Player {
    //Fields
    private String id;
    private boolean landedOnBadSpot = false;
    private boolean landedOnGoodSpot = false;
    private int rolledValue;
    private final String[] player = new String[21];
    private int nextSpot;
    private int previousSpot;
    private Collection<Integer> badSpots = Arrays.asList(3, 11, 19);
    private Collection<Integer> goodSpots = Arrays.asList(4, 8, 12, 16);

    //Constructors
    public Player(String id) {
        this.id = id;
    }

    //Business Methods
    /**
     * initializes the player array to an empty array and puts player in the front
     */
    public void playerStart() {
        Arrays.fill(player, Cell.REGULAR.getText());
        player[0] = getId();
    }

    /**
     * updates the player array based on the dice rolls, and sets the booleans for badSpot and goodSpot landings
     */
    public void playerMove() {
        int currentSpot = Arrays.asList(player).indexOf(getId());
        previousSpot = currentSpot;
        rolledValue = Dice.rollDice();
        nextSpot = currentSpot + rolledValue;
        System.out.println("Player:"+ getId() + " rolled a " + rolledValue);
        Arrays.fill(player, Cell.REGULAR.getText());
        if (nextSpot < 20) {
            if(goodSpots.contains(nextSpot)){
                setLandedOnGoodSpot(true);
                nextSpot = nextSpot+2;
                player[nextSpot] = getId();}
            if(badSpots.contains(nextSpot)){
                setLandedOnBadSpot(true);
                nextSpot = 0;
            }
            player[nextSpot] = getId();
        }
    }

    //accessors
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

    public boolean isLandedOnBadSpot() {
        return landedOnBadSpot;
    }

    public void setLandedOnBadSpot(boolean landedOnBadSpot) {
        this.landedOnBadSpot = landedOnBadSpot;
    }

    public boolean isLandedOnGoodSpot() {
        return landedOnGoodSpot;
    }

    public void setLandedOnGoodSpot(boolean landedOnGoodSpot) {
        this.landedOnGoodSpot = landedOnGoodSpot;
    }

}



