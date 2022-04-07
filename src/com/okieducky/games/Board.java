package com.okieducky.games;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private final String[] track = new String[21];
    private final Player p1 = new Player(Cell.DUCKY.getText());
    private final Player p2 = new Player(Cell.DUCKY2.getText());
    Prompter prompter = new Prompter(new Scanner(System.in));

    public void createAscii(String fileName) {
        try {
            Files.lines(Path.of(fileName))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printTrack() {
        Arrays.fill(track, Cell.REGULAR.getText());
        for (int i = 0; i < 5; i++) {
            track[i * 4] = Cell.SAFE.getText();
        }
        track[20] = Cell.WIN.getText();
        track[3] = Cell.PENALTY.getText();
        track[11] = Cell.PENALTY.getText();
        track[19] = Cell.PENALTY.getText();
        System.out.println(Arrays.toString(track).replace(",", ""));
    }

    public void printPlayer() {
        String input;
        p1.playerStart();
        p2.playerStart();
        createAscii("duch.txt");
        printInstructions();
        printBoard();
        do {
            System.out.println();
            input = prompter.prompt("Hit Q to quit game or press ENTER to roll dice, Player1 " + p1.getId());
            if(input.equalsIgnoreCase("q")){
                Console.clear();
                System.out.println(p1.getId() + " is a quitter...game over!!!");
                return;
            }
            p1.playerMove();
            animateMove(p1, p2, 1);
            Console.clear();
            System.out.println("Player:" + p1.getId() + " rolled a " + p1.getRolledValue());
            printBoard();
            if(isWinner(p1)) {
                break;
            }
            input = prompter.prompt("Hit Q to quit game or press ENTER to roll dice, Player1 " + p2.getId());
            if(input.equalsIgnoreCase("q")){
                Console.clear();
                System.out.println(p2.getId() + " is a quitter...game over!!!");
                return;
            }
            p2.playerMove();
            animateMove(p1, p2, 2);
            Console.clear();
            System.out.println("Player:" + p2.getId() + " rolled a " + p2.getRolledValue());
            printBoard();
            if(isWinner(p2)){
                break;
            }
        }while(!input.equalsIgnoreCase("q"));
    }

    private void printInstructions() {
        System.out.println("The goal of the game is to make it to the finish line.\n" +
                "Each player will take turns rolling a dice containing numbers 1 THROUGH 4.\n" +
                "GREEN SAFE spots give you a Boost of 2 spots!!\n" +
                "While RED PENALTY spots take you back to the Starting Spot:(\n" +
                "Racers to the starting line!!!!!");
        System.out.println();
    }

    public void animateMove(Player p1, Player p2, int movingPlayer) {
        if(movingPlayer == 1){
            if(p1.getPreviousSpot() >= p1.getNextSpot()){
                animateTopForward(p1, p2, p1.getPreviousSpot(), p1.getPreviousSpot() + p1.getRolledValue());
                animateTopBackward(p1, p2, p1.getPreviousSpot() + p1.getRolledValue(), p1.getNextSpot());
            }
            else{
                animateTopForward(p1, p2, p1.getPreviousSpot(), p1.getNextSpot());
            }
        }else{
            if(p2.getPreviousSpot() >= p2.getNextSpot()){
                animateBotForward(p1, p2, p2.getPreviousSpot(), p2.getRolledValue() + p2.getPreviousSpot());
                animateBotBackward(p1, p2, p2.getPreviousSpot() + p2.getRolledValue(), p2.getNextSpot());
            }
            else{
                animateBotForward(p1, p2, p2.getPreviousSpot(), p2.getNextSpot());
            }
        }
    }

    private void animateBotBackward(Player top, Player bot, int previousSpot, int nextSpot) {
        String[] mirrorPlayer = Arrays.copyOf(bot.getPlayer(), bot.getPlayer().length);
        for (int i = previousSpot; i > nextSpot; i--) {
            Console.clear();
            Arrays.fill(mirrorPlayer, Cell.REGULAR.getText());
            System.out.println("Player:" + bot.getId() + " rolled a " + bot.getRolledValue());
            mirrorPlayer[i] = Cell.DUCKY2.getText();
            System.out.println(Arrays.toString(top.getPlayer()).replace(",", ""));
            printTrack();
            System.out.println(Arrays.toString(mirrorPlayer).replace(",", ""));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void animateTopBackward(Player top, Player bot, int previousSpot, int nextSpot) {
        String[] mirrorPlayer = Arrays.copyOf(top.getPlayer(), top.getPlayer().length);
        for (int i = previousSpot; i > nextSpot; i--) {
            Console.clear();
            Arrays.fill(mirrorPlayer, Cell.REGULAR.getText());
            System.out.println("Player:" + top.getId() + " rolled a " + top.getRolledValue());
            mirrorPlayer[i] = Cell.DUCKY.getText();
            System.out.println(Arrays.toString(mirrorPlayer).replace(",", ""));
            printTrack();
            System.out.println(Arrays.toString(bot.getPlayer()).replace(",", ""));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void animateBotForward(Player top, Player bot, int previousSpot, int nextSpot) {
        String[] mirrorPlayer = Arrays.copyOf(bot.getPlayer(), bot.getPlayer().length);
        for (int i = previousSpot; i <= nextSpot; i++) {
            if(i > 20){
                break;
            }
            Console.clear();
            Arrays.fill(mirrorPlayer, Cell.REGULAR.getText());
            System.out.println("Player:" + bot.getId() + " rolled a " + bot.getRolledValue());
            mirrorPlayer[i] = Cell.DUCKY2.getText();
            System.out.println(Arrays.toString(top.getPlayer()).replace(",", ""));
            printTrack();
            System.out.println(Arrays.toString(mirrorPlayer).replace(",", ""));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void animateTopForward(Player top, Player bot, int previousSpot, int nextSpot){
        String[] mirrorPlayer = Arrays.copyOf(top.getPlayer(), top.getPlayer().length);
        for (int i = previousSpot; i <= nextSpot; i++) {
            if(i > 20){
                break;
            }
            Console.clear();
            Arrays.fill(mirrorPlayer, Cell.REGULAR.getText());
            System.out.println("Player:" + top.getId() + " rolled a " + top.getRolledValue());
            mirrorPlayer[i] = Cell.DUCKY.getText();
            System.out.println(Arrays.toString(mirrorPlayer).replace(",", ""));
            printTrack();
            System.out.println(Arrays.toString(bot.getPlayer()).replace(",", ""));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }


    public void printBoard() {
        if (p1.isLandedOnBadSpot()) {
            System.out.println(p1.getId() + "Landed in a Penalty spot and was sent back to the start!!!");
            p1.setLandedOnBadSpot(false);
        } else if (p2.isLandedOnBadSpot()) {
            System.out.println(p2.getId() + "Landed in a Penalty spot and was sent back to the start!!!");
            p2.setLandedOnBadSpot(false);
        }
        System.out.println(Arrays.toString(p1.getPlayer()).replace(",", ""));
        printTrack();
        System.out.println(Arrays.toString(p2.getPlayer()).replace(",", ""));
    }

    public boolean isWinner(Player player){
        if(player.getNextSpot() >= 20){
            Console.clear();
            System.out.println("Player " + player.getId() + " is the Winner!");
            return true;
        }
        return false;
    }

}