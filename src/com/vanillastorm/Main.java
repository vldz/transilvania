package com.vanillastorm;

import com.vanillastorm.Util.CreatureColor;

public class Main {

    public static void main(String[] args) {
        Creature vlad = new Creature("Vlad", 100, 10, 7, 1, 0, CreatureColor.CYAN);
        Creature warewolf = new Creature("Warewolf", 100, 5, 10, 1, 0, CreatureColor.PURPLE);

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
