package com.vanillastorm;

import com.vanillastorm.creatures.evilCreatures.Villain;
import com.vanillastorm.creatures.goodCreatures.Hero;
import com.vanillastorm.util.CreatureColor;

public class Main {

    public static void main(String[] args) {
        Hero vlad = new Hero("Detective");
        Villain werewolf = new Villain("Werewolf", 50, 1,10,  3, 50, CreatureColor.PURPLE);

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

//TODO: Hero money, Creature gives money after defeat(longer battle = more money?)
//TODO: Do smth if Creature is dead (disability to perform on It attacks, or It cant perform attacks)

//TODO: Dealer to buy levelUp, medkits, shield.

