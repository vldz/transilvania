package com.vanillastorm.gameplay.story;

// extra dialog or redirect on chapter
public class Result {
    private int nextChapterID;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNextChapterID() {
        return this.nextChapterID;
    }

    public void setNextChapterID(int nextChapterID) {
        this.nextChapterID = nextChapterID;
    }
}
