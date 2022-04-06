package com.okieducky.games;

public enum Cell {
    REGULAR("\033[37m[ ]\033[0m"),
    SAFE( "\033[92m[G]\033[0m"),
    PENALTY("\033[91m[B]\033[0m"),
    DUCKY("\033[33m[1]\033[0m"),
    DUCKY2("\033[34m[2]\033[0m"),
    WIN("\033[32m[W]\033[0m");

    private final String text;

    Cell(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
