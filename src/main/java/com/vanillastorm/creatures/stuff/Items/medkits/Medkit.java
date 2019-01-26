package com.vanillastorm.creatures.stuff.Items.medkits;

import com.vanillastorm.creatures.stuff.Items.Item;

public class Medkit extends Item{
    private int healPoints;

    public Medkit(int itemId, String name, int weight, int healPoints) {
        super(itemId, name, weight);
        this.healPoints = healPoints;
    }

    public int getHealPoints() {
        return healPoints;
    }
}
