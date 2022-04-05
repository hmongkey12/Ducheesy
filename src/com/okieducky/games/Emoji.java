package com.okieducky.games;

public enum Emoji {
    TEXT_GREEN("\u001B[32m"),
    TEXT_RED( "\u001B[31m"),
    TEXT_RESET("\u001B[0m"),
    TEXT_WHITE("\u001b[37m");

    public final String text;

    Emoji(String text){
        this.text = text;
    }
}
