package com.vanillastorm.creatures.protagonists;

import com.vanillastorm.creatures.Creature;
import com.vanillastorm.creatures.protagonists.stuff.Backpack;
import com.vanillastorm.util.Color;

public class Hero extends Creature {
    private int gold;

    public Hero(String name, int hp, int level, double strength, int accuracy, String shield, int gold) {
        super(name, hp, level, strength, accuracy, shield, gold, Color.CYAN);
    }

    @Override
    public int getHp() {
        return super.getHp();
    }
}

//TODO: add mana to hero, restore mana with alcohol