package com.okieducky.games;

public class Player {
//Feilds
    public String id;
    private String[] player1 = new String[21];
    private String[] player2 = new String[21];
//Constructors
    public Player1(String id) {
        this.id = id;
    }
    public Player2(String id) {
        this.id = id;
    }
//methods
    public void player1Start(){
        player1[0] = "Z"
    }
    public void player2Start(){
        player2[0] = "T"
    }
    public void player1Move(){
        int nextSpot = Array.IndexOf(Player1,"Z") + new Random().nextInt(3-1 +1) +1
               player1[nextSpot]
    //Random number between 1-3
        // add random number to array
        //Array.IndexOf("Z")
    }
    public void player2Move(){
        int nextSpot = Array.IndexOf(Player2,"T") + new Random().nextInt(3-1 +1) +1
        player2[nextSpot]
        //Random number between 1-3
        // add random number to array
        //Array.IndexOf("Z")
    }
}