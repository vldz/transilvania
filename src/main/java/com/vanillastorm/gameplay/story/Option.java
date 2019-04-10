package com.vanillastorm.gameplay.story;

public class Option {
    private String text;
    private int resultId;

    private boolean canBeInactive;
    private boolean wasUsed;

    private int karmaPoints;

    public void setText(String text) {
        this.text = text;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public String getText() {
        return text;
    }

    public boolean canBeInactive() {
        return canBeInactive;
    }

    public boolean wasUsed() {
        return wasUsed;
    }

    public void setWasUsed(boolean wasUsed) {
        this.wasUsed = wasUsed;
    }

    public void setCanBeInactive(boolean canBeInactive) {
        this.canBeInactive = canBeInactive;
    }

    public int getKarmaPoints() {
        return karmaPoints;
    }

    public void setKarmaPoints(int karmaPoints) {
        this.karmaPoints = karmaPoints;
    }
}
