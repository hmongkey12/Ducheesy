package com.okieducky.games;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

public class Board {
    private String[] track = new String[21];
    private String[] player1 = new String[20];
    private String[] player2 = new String[20];
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";


    public Board(){

    }



    public void printTrack() {
        int i;
        for (i=0;i<21;i++) {
            track[i] = " " + i + " ";
        }


        String safeC = TEXT_GREEN + " \uD83D\uDFE5 ";
        for (i=0;i<6;i++) {
                track[i*4] = safeC;
        }
        String penaltyC = TEXT_RED + " \uD83D\uDFE6";
        track[3] = penaltyC;
        track[11] = penaltyC;
        track[19] = penaltyC;
        System.out.println(Arrays.toString(track)+TEXT_RESET );
    }


    public void printPlayer1(){
        Arrays.fill(player1," . ");
        player1[5] = " X ";
        System.out.println(Arrays.toString(player1));

    }

    public void printPlayer2(){
        Arrays.fill(player2," .. ");
        //player2[0] = "\uD83D\uDFE6 ";
        System.out.println(Arrays.toString(player2));

    }





}