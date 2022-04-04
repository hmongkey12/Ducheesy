package com.okieducky.games;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Player {

    //Fields
    public String id;
    private String[] player1 = new String[20];
    private String[] player2 = new String[20];


    //Constructors
    public Player() {
    }

    public Player(String id) {
        this.id = id;
    }

    public void player1Start() {
        Arrays.fill(player1, "   ");
        player1[0] = "Z";
    }

    //initial point and point to come back after penalty or chase.
    public void player1Move() {
        int currentSpot = Arrays.asList(player1).indexOf("Z");
        int nextSpot = currentSpot + Dice.rollDice();
        Arrays.fill(player1, "   ");
        if(nextSpot<=20) {
            player1[nextSpot] = "Z";
        }
    }


    //getters
    public String[] getPlayer1() {
        return player1;
    }

}


//Random number between 1-3
// add random number to array
//Array.IndexOf("Z")
//    }
//    public void player2Move(){
//        int nextSpot = Array.IndexOf(Player2,"T") + new Random().nextInt(3-1 +1) +1
//        player2[nextSpot]
//        //Random number between 1-3
//        // add random number to array
//        //Array.IndexOf("Z")
//    }
