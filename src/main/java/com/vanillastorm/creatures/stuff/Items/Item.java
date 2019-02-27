package com.vanillastorm.creatures.stuff.Items;

public class Item {
    private int itemId;
    private String name;
    private int weight;
    private int impactPoints;

    public Item(int itemId, String name, int weight, int impactPoints) {
        this.itemId = itemId;
        this.name = name;
        this.weight = weight;
        this.impactPoints = impactPoints;
    }

    public int getWeight() {
        return weight;
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
}

//TODO: add items objects