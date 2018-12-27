package com.vanillastorm.creatures;

public interface Action {
    void attack(Creature creature);
    void takeDamage(double damage);

    void heal(Creature creature, int medkit);

    double generateAccuracy();

    boolean isAlive();
}
