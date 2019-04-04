package com.vanillastorm.gameplay.story;

import com.vanillastorm.creatures.stuff.Items.Item;

// extra dialog and redirect on chapter
public class Result {
    private int nextChapterID;

    private String text;

    private String itemToTake;
    private int amountOfItems;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNextChapterID() {
        return this.nextChapterID;
    }

    public String getItemToTakeName() {
        return itemToTake;
    }

    public void setItemToTake(String itemToTake) {
        this.itemToTake = itemToTake;
    }

    public int getAmountOfItems() {
        return amountOfItems;
    }

    public void setAmountOfItems(int amountOfItems) {
        this.amountOfItems = amountOfItems;
    }

    public void setNextChapterID(int nextChapterID) {
        this.nextChapterID = nextChapterID;
    }
}
