package com.vanillastorm.gameplay.story;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
    private int chapterID;

    private List<Option> options = new ArrayList<>();
    private List<Result> results = new ArrayList<>();

    private String text;

    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int chapterID) {
        this.chapterID = chapterID;
    }

    public void addOption(Option option) {
        this.options.add(option);
    }

//    public String getOptionText (Option option) {
//
//    }

    public void addResult(Result result) {
        this.results.add(result);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
