package com.vanillastorm.creatures;

import com.vanillastorm.creatures.protagonists.stuff.Items.medkits.Medkit;

public interface Action {
    void attack(Creature creature);
    void takeDamage(double damage);

    void heal(Creature creature, Medkit medkit);

    double generateAccuracy();

    boolean isAlive();
}
