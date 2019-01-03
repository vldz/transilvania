package com.vanillastorm.creatures.antagonists;

import com.vanillastorm.creatures.Creature;
import com.vanillastorm.util.Color;

public class Villain extends Creature {
    private int moneyAfterDeath;

    public Villain(String name, int hp, int level, double strength, int accuracy) {
        super(name, hp, level, strength, accuracy, Color.PURPLE);
        this.moneyAfterDeath = 2;

    }

    public int getMoneyAfterDeath() {
        return moneyAfterDeath;
    }

    //TODO: poison, bleed effects(steps)
}
