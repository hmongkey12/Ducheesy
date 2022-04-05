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
        System.out.println(Arrays.toString(p1.getPlayer()));
        printTrack();
        System.out.println(Arrays.toString(p2.getPlayer()));

        while (!input.equals("Q")) {
            System.out.println("Hit Q to quit game and press ENTER to play, player 1");
            input = userInput.nextLine();
            p1.playerMove();

            System.out.println("Player's current location: " + p1.getNextSpot());
             if(goodSpots.contains(p1.getNextSpot())){
                 goodSpotLanding(p1);
             }
             if(badSpots.contains(p1.getNextSpot())){
                badSpotLanding(p1);
             }
            System.out.println();
            System.out.println("Hit Q to quit game and press ENTER to play, player 2");
            input = userInput.nextLine();
            p2.playerMove();
            if(badSpots.contains(p2.getNextSpot())){
                badSpotLanding(p2);
            }
            if(goodSpots.contains(p2.getNextSpot())){
                goodSpotLanding(p2);
            }
            System.out.println(Arrays.toString(p1.getPlayer()));
            printTrack();
            System.out.println(Arrays.toString(p2.getPlayer()));
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


}