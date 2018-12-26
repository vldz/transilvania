package com.vanillastorm;

import com.vanillastorm.Util.CreatureColor;

public class Main {

    public static void main(String[] args) {
        Creature vlad = new Creature("Detective", 100, 10, 7, 1, 10, CreatureColor.CYAN);
        Creature warewolf = new Creature("Warewolf", 100, 5, 10, 1, 20, CreatureColor.PURPLE);

        while (vlad.isAlive() && warewolf.isAlive()) {
            if (vlad.isAlive()) {
                vlad.attack(warewolf);
            }

            if (warewolf.isAlive()) {
                warewolf.attack(vlad);

            }

        }
    }
}
