package com.okieducky.games;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Board {
    private String[] track = new String[21];
    private Player p1 = new Player("1️⃣");
    private Player p2 = new Player("2️⃣");

    Scanner userInput = new Scanner(System.in);
    Collection<Integer> badSpots = Arrays.asList(3, 11, 19);
    Collection<Integer> goodSpots = Arrays.asList(4, 8, 12, 16);




    public Board() {}

    public void printTrack() {
        int i;
//        String regC = "\uD83D\uDFE8";
        String regC = Emoji.REG_C.getText();
        for (i = 0; i < 21; i++) {
            track[i] = regC;
        }

        String safeC= Emoji.SAFE_C.getText();
//      String safeC= "\uD83D\uDFE9";

        for (i = 0; i < 6; i++) {
            track[i * 4] = safeC;
        }

//        String penaltyC = "\uD83D\uDFE5";
        String penaltyC = Emoji.PENALTY_C.getText();
        track[3] = penaltyC;
        track[11] = penaltyC;
        track[19] = penaltyC;
        System.out.println(Arrays.toString(track));
    }

    public void printPlayer() {
        String input = " ";
        p1.playerStart();
        p2.playerStart();
        System.out.println("The goal of the game is to make it to the finish line.");
        System.out.println("Each player will take turns rolling a dice containing numbers 1-3.");
        System.out.println("Green spaces give you a Boost of 2 squares!!");
        System.out.println("While red squares take you back to the starting line :(");
        System.out.println("Racers to the starting line!!!!!");
        System.out.println("");
        printBoard();
        while (!input.equals("Q")) {
            System.out.println("");
            System.out.print("Hit Q to quit game or press ENTER to roll dice, player 1");
            input = userInput.nextLine();
            p1.playerMove();
             if(goodSpots.contains(p1.getNextSpot())){
                 goodSpotLanding(p1);
             }
             if(badSpots.contains(p1.getNextSpot())){
                badSpotLanding(p1);
             }
             printBoard();
            System.out.print("Hit Q to quit game or press ENTER to roll dice, player 2");
            input = userInput.nextLine();
            p2.playerMove();
            if(badSpots.contains(p2.getNextSpot())){
                badSpotLanding(p2);
            }
            if(goodSpots.contains(p2.getNextSpot())){
                goodSpotLanding(p2);
            }
            printBoard();

        }


    }

    public void goodSpotLanding(Player player){
        System.out.println("Good Spot landed, moving 2!!");
        player.updateSpot(2);
    }

    public void badSpotLanding(Player player){
        System.out.println("Bad Spot landed, moving back to start!!");
        player.playerStart();
    }
    public void printBoard(){
        System.out.println(Arrays.toString(p1.getPlayer()));
        printTrack();
        System.out.println(Arrays.toString(p2.getPlayer()));
    }


}