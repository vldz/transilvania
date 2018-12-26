package com.vanillastorm;

public interface Action {
    void attack(Creature creature);
    void takeDamege(double damage);

    void heal(Creature creature, int medkit);

    double generateAccuracy();

    boolean isAlive();
}
