package com.okieducky.games;

public enum Cell {
    REGULAR("\033[37m██\033[0m"),
    SAFE( "\033[92m██\033[0m"),
    PENALTY("\033[91m██\033[0m"),
    DUCKY("\033[33m██\033[0m"),
    DUCKY2("\033[34m██\033[0m");

    private final String text;

    Cell(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
