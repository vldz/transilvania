package com.vanillastorm.creatures.antagonists;

import com.vanillastorm.creatures.Creature;
import com.vanillastorm.util.Color;

public class Villain extends Creature {

    public Villain(String name, int hp, int level, double strength, int accuracy) {
        super(name, hp, level, strength, accuracy, "None", 10, Color.PURPLE);
    }

    //TODO: poison, bleed effects(steps)
}
