package com.vanillastorm.creatures.protagonists.stuff.Items.medkits;

import com.vanillastorm.creatures.protagonists.stuff.Items.Item;

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
