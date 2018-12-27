package com.vanillastorm.creatures.evilCreatures;

import com.vanillastorm.creatures.Creature;

public class Villain extends Creature {
    private int moneyAfterDeath;

    public Villain(String name, int hp, int level, double strength, int accuracy, int defence, String color) {
        super(name, hp, level, strength, accuracy, defence, color);
        this.moneyAfterDeath = 2;
    }

    public int getMoneyAfterDeath() {
        return moneyAfterDeath;
    }

    //TODO: poison, bleed effects(steps)
}
