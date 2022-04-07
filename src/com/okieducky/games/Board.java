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

    /**
     * Reads from a file and prints the file contents.  Used for printing Ascii art.
     * @param fileName a String that has the file location
     * */
    public void createAscii(String fileName) {
        try {
            Files.lines(Path.of(fileName))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a String array that defines the squares of the board.  Shows the square colors
     * and letters.
     */
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

    /**
     * Initializes the game board.  Also runs the game and prompts user for input.  Updates the board based
     * on player input.  Moves the player after the player rolls a dice.
     */
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
                break;
            }
            p1.playerMove();
            animateMove(p1, p2, 1);
            Console.clear();
            System.out.println("Player:" + p1.getId() + " rolled a " + p1.getRolledValue());
            printBoard();
            if(isWinner(p1)) {
                break;
            }
            input = prompter.prompt("Hit Q to quit game or press ENTER to roll dice, Player2 " + p2.getId());
            if(input.equalsIgnoreCase("q")){
                Console.clear();
                System.out.println(p2.getId() + " is a quitter...game over!!!");
                break;
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

    /**
     * Prints the instructions for the player
     */
    private void printInstructions() {
        System.out.println("The goal of the game is to make it to the LAST[w] spot.\n" +
                "Each player will take turns rolling a dice containing numbers 1 THROUGH 4.\n" +
                "GREEN SAFE[S] spots give you a Boost of 2 spots!!\n" +
                "While RED PENALTY[P] spots take you back to the Starting Spot:(\n" +
                "Racers to the starting line!!!!!");
        System.out.println();
    }

    /**
     * animateMove will print the movement of the player from on its previous location to its next location
     * @param p1 Player class, used to access player fields for player 1
     * @param p2 Player class, used to access player fields for player 2
     * @param movingPlayer Boolean that lets the board keep track of which player to update animations for
     */
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

    /**
     * Prints the movement of the player backwards from the previousSpot to the nextSpot
     * @param top Player that will be displayed on the top
     * @param bot Player that will be displayed on the bottom
     * @param previousSpot The previous spot of the player being moved
     * @param nextSpot The next spot of the player being moved
     */
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

    /**
     * Prints the movement of the player backwards from the previousSpot to the nextSpot
     * @param top Player that will be displayed on the top
     * @param bot Player that will be displayed on the bottom
     * @param previousSpot The previous spot of the player being moved
     * @param nextSpot The next spot of the player being moved
     */
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


    /**
     * Prints the movement of the player from the previousSpot to the nextSpot
     * @param top Player that will be displayed on the top
     * @param bot Player that will be displayed on the bottom
     * @param previousSpot The previous spot of the player being moved
     * @param nextSpot The next spot of the player being moved
     */
    private void animateBotForward(Player top, Player bot, int previousSpot, int nextSpot) {
        String[] mirrorPlayer = Arrays.copyOf(bot.getPlayer(), bot.getPlayer().length);
        for (int i = previousSpot; i <= nextSpot; i++) {
            if(i > 20){
                break;
            }
            Console.clear();
            Arrays.fill(mirrorPlayer, Cell.REGULAR.getText());
            if(bot.isLandedOnGoodSpot()){
                System.out.println("Good Spot landed, moving 2!!");
            }
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

    /**
     * Prints the movement of the player from the previousSpot to the nextSpot
     * @param top Player that will be displayed on the top
     * @param bot Player that will be displayed on the bottom
     * @param previousSpot The previous spot of the player being moved
     * @param nextSpot The next spot of the player being moved
     */
    public void animateTopForward(Player top, Player bot, int previousSpot, int nextSpot){
        String[] mirrorPlayer = Arrays.copyOf(top.getPlayer(), top.getPlayer().length);
        for (int i = previousSpot; i <= nextSpot; i++) {
            if(i > 20){
                break;
            }
            Console.clear();
            Arrays.fill(mirrorPlayer, Cell.REGULAR.getText());
            if(top.isLandedOnGoodSpot()){
                System.out.println("Good Spot landed, moving extra 2!!");
            }
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


    /**
     * Prints the current state of the board based on the players locations
     */
    public void printBoard() {
        if (p1.isLandedOnBadSpot()) {
            System.out.println(p1.getId() + "Landed in a Penalty spot and was sent back to the start!!!");
            p1.setLandedOnBadSpot(false);
        } else if (p2.isLandedOnBadSpot()) {
            System.out.println(p2.getId() + "Landed in a Penalty spot and was sent back to the start!!!");
            p2.setLandedOnBadSpot(false);
        } else if (p1.isLandedOnGoodSpot()) {
            System.out.println("Good Spot landed, moved extra 2!!");
            p1.setLandedOnGoodSpot(false);
        } else if (p2.isLandedOnGoodSpot()) {
            System.out.println("Good Spot landed, moved extra 2!!");
            p2.setLandedOnGoodSpot(false);
        }
        System.out.println(Arrays.toString(p1.getPlayer()).replace(",", ""));
        printTrack();
        System.out.println(Arrays.toString(p2.getPlayer()).replace(",", ""));
    }

    /**
     * Checks to see if the player is a winner
     * @param player the player will contain the winner boolean and it will be true if they go past the winning spot
     * @return boolean
     */
    public boolean isWinner(Player player){
        if(player.getNextSpot() >= 20){
            Console.clear();
            createAscii("winner.txt");
            System.out.println("Player " + player.getId() + " is the Winner!");
            return true;
        }
        return false;
    }

}