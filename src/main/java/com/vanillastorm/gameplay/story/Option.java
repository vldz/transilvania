package com.vanillastorm.gameplay.story;

public class Option {
    private String text;
    private int resultId;

    public void setText(String text) {
        this.text = text;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public String getText() {
        return text;
    }
}
