package com.okieducky.games;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

public class Board {
    private String[] track = new String[21];
    private String[] player1 = new String[21];
    private String[] player2 = new String[21];


    public Board(){

    }



    public void printTrack() {
        Arrays.fill(track, " . ");
        System.out.println(Arrays.toString(track));

    }

    public void printPlayer1(){
        Arrays.fill(player1," . ");
        player1[5] = "\uD83D\uDFE5 ";
        System.out.println(Arrays.toString(player1));

    }

    public void printPlayer2(){
        Arrays.fill(player2," . ");
        player2[0] = "\uD83D\uDFE6 ";
        System.out.println(Arrays.toString(player2));

    }





}