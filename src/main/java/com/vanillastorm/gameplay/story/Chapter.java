package com.vanillastorm.gameplay.story;

import java.util.*;

public class Chapter {
    private List<Option> options = new ArrayList<>();
    private List<Result> results = new ArrayList<>();

    private String text;
    private boolean isRestart;

    public void addOption(Option option) {
        this.options.add(option);
    }

    public void addResult(Result result) {
        this.results.add(result);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRestart() {
        return isRestart;
    }

    public void setRestart(boolean restart) {
        isRestart = restart;
    }

    public String optionText(int optionNumber) {
        return this.options.get(optionNumber).getText();
    }

    public int getResultAmount() {
        return this.results.size();
    }

    public int getResultIDByOptionText(String text) {
        int resultId = 0;
        while (resultId < this.results.size()) {
            if(options.get(resultId).getText().equals(text)) {
                return resultId;
            } else {
                resultId++;
            }
        }
        return 0;
    }

    public Result getResultN (int n) {
        return results.get(n);
    }

    public String getResultText(int resultId) {
        return this.results.get(resultId).getText();
    }

    public String getAnswerResult (String message) {
        return this.getResultText(getResultIDByOptionText(message));
    }

    public int getNextChapter (String text) {
        int resultID = getResultIDByOptionText(text);
        return this.results.get(resultID).getNextChapterID();
    }
}
