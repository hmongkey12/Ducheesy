package com.okieducky.games;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Board {
    private String[] track = new String[21];
    private Player p1 = new Player(Cell.DUCKY.getText());
    private Player p2 = new Player(Cell.DUCKY2.getText());

    Scanner userInput = new Scanner(System.in);

    public Board() {

    }

    public void printTrack() {
        int i;

        String regC = Cell.REGULAR.getText();
        for (i = 0; i < 21; i++) {
            track[i] = regC;
        }

        String safeC= Cell.SAFE.getText();


        for (i = 0; i < 6; i++) {
            track[i * 4] = safeC;
        }


        String penaltyC = Cell.PENALTY.getText();
        track[3] = penaltyC;
        track[11] = penaltyC;
        track[19] = penaltyC;
        System.out.println(Arrays.toString(track));
    }

    public void printPlayer() {
        String input = " ";
        p1.playerStart();
        p2.playerStart();
        System.out.println("The goal of the game is to make it to the finish line.\n"+
                "Each player will take turns rolling a dice containing numbers 1-3.\n"+
                "Green spaces give you a Boost of 2 squares!!\n"+
                "While red squares take you back to the starting line :(\n"+
                "Racers to the starting line!!!!!");
        System.out.println("");
        printBoard();
        while (!input.equals("Q")) {
            System.out.println("");
            System.out.print("Hit Q to quit game or press ENTER to roll dice, Player1 "+ p1.id);
            input = userInput.nextLine();
            p1.playerMove();
             printBoard();
            System.out.print("Hit Q to quit game or press ENTER to roll dice, Player2 " + p2.id);
            input = userInput.nextLine();
            p2.playerMove();
            printBoard();
        }

    }

    public void printBoard(){
        System.out.println(Arrays.toString(p1.getPlayer()));
        printTrack();
        System.out.println(Arrays.toString(p2.getPlayer()));
    }


}