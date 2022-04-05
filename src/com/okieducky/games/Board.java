package com.okieducky.games;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Board {
    private String[] track = new String[21];
    private Player p1 = new Player("1");
    private Player p2 = new Player("2");

//    public static final String TEXT_GREEN = "\u001B[32m";
//    public static final String TEXT_RED = "\u001B[31m";
//    public static final String TEXT_RESET = "\u001B[0m";
//    public static final String TEXT_WHITE = "\u001b[37m";
    Scanner userInput = new Scanner(System.in);
    public final int badSpot1 = 3;
    public final int badSpot2 = 11;
    public final int badSpot3 = 19;
    public final int goodSpot1 = 4;
    public final int goodSpot2 = 12;
    public final int goodSpot3 = 16;



    public Board() {

    }


    public void printTrack() {
        int i;
        String regC = "\uD83D\uDFE8";
        for (i = 0; i < 21; i++) {
            track[i] = regC;
        }

        String safeC= "\uD83D\uDFE9";
        for (i = 0; i < 6; i++) {
            track[i * 4] = safeC;
        }

        String penaltyC = "\uD83D\uDFE5";
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
        System.out.println(Arrays.toString(p2.getPlayer()));

        while (!input.equals("Q")) {
            System.out.println("Hit Q to quit game and press ENTER to play, player 1");
            input = userInput.nextLine();
             p1.playerMove();
            System.out.println();
            if(p1.getNextSpot() == badSpot1){
                p1.playerStart();
            }
            if(p1.getNextSpot() == badSpot2){
                p1.playerStart();
            }
            if(p1.getNextSpot() == badSpot3){
                p1.playerStart();
            }
//            if (p1.getNextSpot() == goodSpot1){
//                p1.setNextSpot(p1.getNextSpot() + 2);
//            }
//            if (p1.getNextSpot() == goodSpot2){
//                p1.setNextSpot(p1.getNextSpot() + 2);
//            }
//            if (p1.getNextSpot() == goodSpot3){
//                p1.setNextSpot(p1.getNextSpot() + 2);
//            }

            System.out.println("Player 1 "+ p1.getNextSpot());
            System.out.println("Next Spot for player " + p1.getId() + ":" + p1.getNextSpot());
            System.out.println("Hit Q to quit game and press ENTER to play, player 2");
            input = userInput.nextLine();
            p2.playerMove();
            System.out.println("next spot"+ p2.getNextSpot());
            if(p2.getNextSpot() == badSpot1){
                p2.playerStart();
            }
            if(p2.getNextSpot() == badSpot2){
                p2.playerStart();
            }
            if(p2.getNextSpot() == badSpot3){
                p2.playerStart();
            }
//            if (p2.getNextSpot() == goodSpot1){
//                System.out.println("NextSpot = "+ p2.getNextSpot());
//                p2.setNextSpot(p2.getNextSpot() + 2);
//                System.out.println("NextSpot = "+ p2.getNextSpot());
//            }
//            if (p2.getNextSpot() == goodSpot2){
//                System.out.println("NextSpot = "+ p2.getNextSpot());
//                p2.setNextSpot(p2.getNextSpot() + 2);
//                System.out.println("NextSpot = "+p2.getNextSpot());
//            }
//            if (p2.getNextSpot() == goodSpot3){
//                System.out.println("NextSpot = "+ p2.getNextSpot());
//                p2.setNextSpot(p2.getNextSpot() + 2);
//                System.out.println("NextSpot = "+ p2.getNextSpot());
//            }
            System.out.println("Next Spot for player " + p2.getId() + ":" + p2.getNextSpot());
            System.out.println(Arrays.toString(p1.getPlayer()));
            printTrack();
            System.out.println(Arrays.toString(p2.getPlayer()));
        }


    }


}