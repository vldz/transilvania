package com.vanillastorm.gameplay.story;

import java.util.*;

public class Chapter {
    private List<Option> options = new ArrayList<>();
    private List<Result> results = new ArrayList<>();

//    private List<String> fighters = new ArrayList<>();

    private String text;
    private boolean isFightChapter;

    public void addOption(Option option) {
        this.options.add(option);
    }

    public void addResult(Result result) {
        this.results.add(result);
    }

//    public void addFighter(String nameOfFighter) {
//        this.fighters.add(nameOfFighter);
//    }

    public boolean isFightChapter() {
        return this.isFightChapter;
    }

    public void setFightChapter(boolean fightChapter) {
        isFightChapter = fightChapter;
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

    public int getResultsAmount() {
        return this.results.size();
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
        return this.results.get(resultId).getText();
    }

    public String getAnswerResult (String message) {
        return this.getResultText(getResultIDByOptionText(message));
    }

    public int getNextChapter (String text) {
//        if (this.fighters.size() == 0) {
//            System.out.println("null fighters");
//        } else {
//            for (int i = 0; i < fighters.size(); i++) {
//                System.out.println("fighter " + i + ": " + fighters.get(i));
//            }
//        }
        int resultID = getResultIDByOptionText(text);
        return this.results.get(resultID).getNextChapterID();
    }

    public Result getFirstResult(){
        return results.get(0);
    }
}
