package com.vanillastorm.gameplay.story;

// extra dialog or redirect on chapter
public class Result {
    private double resultID;
    private int nextChapterID;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getResultID() {
        return resultID;
    }

    public void setResultID(double resultID) {
        this.resultID = resultID;
    }

    public int getNextChapterID() {
        return nextChapterID;
    }

    public void setNextChapterID(int nextChapterID) {
        this.nextChapterID = nextChapterID;
    }
}
