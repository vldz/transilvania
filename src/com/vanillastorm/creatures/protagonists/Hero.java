package com.vanillastorm.creatures.protagonists;

import com.vanillastorm.creatures.Creature;
import com.vanillastorm.util.Color;

public class Hero extends Creature {
    private int gold;
    //private Backpack backpack;

    public Hero(String name) {
        super(name, 100, 1, 10, 5, 100, Color.CYAN);
        this.gold = 5;
    //    this.backpack = backpack;
    }

    public void setGold(int gold) {
        this.gold += gold;
    }

    public int getGold() {
        return gold;
    }

    //TODO: For Hero make ability to find out have many health points has character
    //TODO: What is in backpack(Backpack)
    //TODO: Backpack
}
