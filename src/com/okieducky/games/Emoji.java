package com.okieducky.games;

public enum Emoji {
    REG_C("\uD83D\uDFE8"),
    SAFE_C( "\uD83D\uDFE9"),
    PENALTY_C("\uD83D\uDFE5");

    private final String text;

    Emoji(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
