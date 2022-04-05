package com.okieducky.games;

import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private String[] track = new String[21];

    private Player p1 = new Player("1");
    private Player p2 = new Player("2");

    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_WHITE = "\u001b[37m";
    Scanner userInput = new Scanner(System.in);


    public Board() {

    }


    public void printTrack() {
        int i;
        String regC = TEXT_WHITE + "\uD83D\uDFE5";
        for (i = 0; i < 21; i++) {
            track[i] = regC;
        }

        String safeC = TEXT_GREEN + "\uD83D\uDFE5";
        for (i = 0; i < 6; i++) {
            track[i * 4] = safeC;
        }

        String penaltyC = TEXT_RED + "\uD83D\uDFE6";
        track[3] = penaltyC;
        track[11] = penaltyC;
        track[19] = penaltyC;
        System.out.println(Arrays.toString(track) + TEXT_RESET);
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
            System.out.println("Next Spot for player " + p1.getId() + ":" + p1.nextSpot);
            System.out.println("Hit Q to quit game and press ENTER to play, player 2");

            input = userInput.nextLine();
            p2.playerMove();
            System.out.println("Next Spot for player " + p2.getId() + ":" + p2.nextSpot);

            System.out.println(Arrays.toString(p1.getPlayer()));
            printTrack();
            System.out.println(Arrays.toString(p2.getPlayer()));


        }


    }


}