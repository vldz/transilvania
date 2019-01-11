package com.vanillastorm.creatures.protagonists.stuff.Items;


public class Item {
    private int itemId;
    String name;
    private int weight;

    public Item(int itemId, String name, int weight) {
        this.itemId = itemId;
        this.name = name;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }
}

//TODO: add items objects