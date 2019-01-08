package com.vanillastorm;

import com.vanillastorm.creatures.antagonists.Villain;
import com.vanillastorm.creatures.protagonists.Hero;
import com.vanillastorm.creatures.protagonists.characters.Detective;

public class Main {

    public static void main(String[] args) {
        Hero vlad = new Detective();
        Villain werewolf = new Villain("Werewolf", 100, 2,20,  5);

        while (vlad.isAlive() && werewolf.isAlive()) {
            if (vlad.isAlive()) {
                vlad.attack(werewolf);
            }
            if (werewolf.isAlive()) {
                werewolf.attack(vlad);
            }

        }
    }
}


