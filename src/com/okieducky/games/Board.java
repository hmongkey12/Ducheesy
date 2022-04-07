package com.okieducky.games;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private String[] track = new String[21];
    private Player p1 = new Player(Cell.DUCKY.getText());
    private Player p2 = new Player(Cell.DUCKY2.getText());
    Scanner userInput = new Scanner(System.in);
    Prompter prompter = new Prompter(new Scanner(System.in));

    public Board() {

    }

    public void printTrack() {
        int i;

        String regC = Cell.REGULAR.getText();
        for (i = 0; i < 21; i++) {
            track[i] = regC;
        }

        String safeC= Cell.SAFE.getText();

        for (i = 0; i < 5; i++) {
            track[i * 4] = safeC;
        }

        track[20] = Cell.WIN.getText();
        String penaltyC = Cell.PENALTY.getText();
        track[3] = penaltyC;
        track[11] = penaltyC;
        track[19] = penaltyC;
        System.out.println(Arrays.toString(track).replace(",", ""));
    }

    public void printPlayer() {
        String input = " ";
        p1.playerStart();
        p2.playerStart();
        System.out.println("The goal of the game is to make it to the finish line.\n"+
                "Each player will take turns rolling a dice containing numbers 1 THROUGH 4.\n"+
                "GREEN OR SAFE spaces give you a Boost of 2 squares!!\n"+
                "While RED or PENALTY spots take you back to the starting spot :(\n"+
                "Racers to the starting line!!!!!");
        System.out.println("");
        printBoard();
        while (!input.equalsIgnoreCase("q") && !(p1.getWin()) || p2.getWin()) {
            System.out.println("");
            input = prompter.prompt("Hit Q to quit game or press ENTER to roll dice, Player1 "+ p1.getId());
            p1.playerMove();
            animateMove(p1, p2, 1);
            Console.clear();
            printBoard();
            input = prompter.prompt("Hit Q to quit game or press ENTER to roll dice, Player1 "+ p2.getId());
            p2.playerMove();
            animateMove(p1, p2, 2);
            Console.clear();
            printBoard();
        }
        printWin(p1, p2);
    }

    public void animateMove(Player p1, Player p2, int movingPlayer){
        String [] mirrorPlayer;
        String movingId;
        int previousSpot;
        int currentSpot;
        int rollingValue;
        if(movingPlayer == 1){
            mirrorPlayer = Arrays.copyOf(p1.getPlayer(), p1.getPlayer().length);
            movingId = p1.getId();
            previousSpot = p1.getPreviousSpot();
            currentSpot = p1.getNextSpot();
            rollingValue = p1.getRolledValue();
        }else{
            mirrorPlayer = Arrays.copyOf(p2.getPlayer(), p2.getPlayer().length);
            rollingValue = p2.getRolledValue();
            movingId = p2.getId();
            previousSpot = p2.getPreviousSpot();
            currentSpot = p2.getNextSpot();
        }
        Arrays.asList(mirrorPlayer).indexOf(movingId);
        //TODO - if current spot is over 21, then they should win and shouldn't keep moving
        for(int i = previousSpot; i < currentSpot; i++){
            Console.clear();
            Arrays.fill(mirrorPlayer, Cell.REGULAR.getText());
            if(movingPlayer == 1){
                System.out.println("Player:"+ movingId + " rolled a " + rollingValue);
                mirrorPlayer[i] = Cell.DUCKY.getText();
                System.out.println(Arrays.toString(mirrorPlayer).replace(",", ""));
                printTrack();
                System.out.println(Arrays.toString(p2.getPlayer()).replace(",", ""));
            }else{
                System.out.println("Player:"+ movingId + " rolled a " + rollingValue);
                mirrorPlayer[i] = Cell.DUCKY2.getText();
                System.out.println(Arrays.toString(p1.getPlayer()).replace(",", ""));
                printTrack();
                System.out.println(Arrays.toString(mirrorPlayer).replace(",", ""));
            }
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void printBoard(){
        if(p1.isLandedOnBadSpot()){
            System.out.println(p1.getId() + "Bad Spot landed, moving back to start!!");
            p1.setLandedOnBadSpot(false);
        }
        else if(p2.isLandedOnBadSpot()){
            System.out.println(p2.getId() + "Bad Spot landed, moving back to start!!");
            p2.setLandedOnBadSpot(false);
        }
        System.out.println(Arrays.toString(p1.getPlayer()).replace(",", ""));
        printTrack();
        System.out.println(Arrays.toString(p2.getPlayer()).replace(",", ""));
    }

    public void printWin(Player p1, Player p2){
       if(p1.getWin()) {
           System.out.println("Player " + p1.getId() + " is the Winnner!");
       }
       else{
           System.out.println("Player " + p2.getId() + " is the Winnner!");
       }
    }
}