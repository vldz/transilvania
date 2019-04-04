package com.vanillastorm.gameplay.story;

import java.util.*;

public class Chapter {
    private List<Option> options = new ArrayList<>();
    private List<Result> results = new ArrayList<>();
    private String text;

    private boolean isFightChapter;

    private String imageURL;

    private int opensChapterN;

    private String password;
    private boolean requiresPassword;

    public void addOption(Option option) {
        this.options.add(option);
    }

    public void addResult(Result result) {
        this.results.add(result);
    }

    public boolean isFightChapter() {
        return this.isFightChapter;
    }

    public void setFightChapter(boolean fightChapter) {
        isFightChapter = fightChapter;
    }

    public String getPassword() {
        return password;
    }

    public void setRequiresPassword(boolean requiresPassword) {
        this.requiresPassword = requiresPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPhotoChapter() {
        return (this.imageURL != null);
    }

    public boolean isPasswordChapter() {
        return requiresPassword;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String optionText(int optionNumber) {
        return this.options.get(optionNumber).getText();
    }

    public int getOptionsAmount() {
        return this.options.size();
    }

    public int getResultIDByOptionText(String text) {
        int resultId = 0;
        while (resultId < this.results.size()) {
            if (options.get(resultId).getText().equals(text)) {
                return resultId;
            } else {
                resultId++;
            }
        }
        return 0;
    }

    public String getResultText(int resultId) {
        if (this.options.get(resultId).canBeInactive()) {
            this.options.get(resultId).setWasUsed(true);
        }
        return this.results.get(resultId).getText();
    }

    public String getResultsItemToTake(String message) {
        int resultId = getResultIDByOptionText(message);
        return this.results.get(resultId).getItemToTakeName();
    }

    public int getResultsAmountOfItemToTake(String message) {
        int resultId = getResultIDByOptionText(message);
        return this.results.get(resultId).getAmountOfItems();
    }

    public void swapFirstAndLastResults() {
        this.results.get(0).setText(this.results.get(this.results.size() - 1).getText());
        this.results.get(0).setNextChapterID(this.results.get(this.results.size() - 1).getNextChapterID());
    }

    public String getAnswerResult (String message) {
        return this.getResultText(getResultIDByOptionText(message));
    }

    public int getNextChapter (String text) {
        int resultID = getResultIDByOptionText(text);
        return this.results.get(resultID).getNextChapterID();
    }

    public Result getFirstResult(){
        return results.get(0);
    }

    public int getOpensChapterN() {
        return opensChapterN;
    }

    public void setOpensChapterN(int opensChapterN) {
        this.opensChapterN = opensChapterN;
    }

    public List<Option> getOptions() {
        return options;
    }
}
