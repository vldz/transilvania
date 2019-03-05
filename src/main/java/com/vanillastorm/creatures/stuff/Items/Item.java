package com.vanillastorm.creatures.stuff.Items;

public class Item {
    private int itemId;
    private String name;
    private int impactPoints;

    public Item(int itemId, String name, int impactPoints) {
        this.itemId = itemId;
        this.name = name;
        this.impactPoints = impactPoints;
    }

    public String getName() {
        return name;
    }

    public int getImpactPoints() {
        return impactPoints;
    }

    public int getItemId() {
        return itemId;
    }

    public boolean isMedicine() {
        return (this.getItemId() == 0) || (this.getItemId() == 1) || (this.getItemId() == 2);
    }

    public boolean isManaBooster() {
        return (this.getItemId() == 3) || (this.getItemId() == 4) || (this.getItemId() == 5);
    }
}

//TODO: add items objects