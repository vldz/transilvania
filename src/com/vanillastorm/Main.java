package com.vanillastorm;

import com.vanillastorm.creatures.antagonists.Villain;
import com.vanillastorm.creatures.protagonists.Hero;
import com.vanillastorm.creatures.protagonists.stuff.Backpack;
import com.vanillastorm.util.Color;

public class Main {

    public static void main(String[] args) {
        Hero vlad = new Hero("Detective");
        Villain werewolf = new Villain("Werewolf", 50, 1,10,  3, 50, Color.PURPLE);

        while (vlad.isAlive() && werewolf.isAlive()) {
            if (vlad.isAlive()) {
                vlad.attack(werewolf);
            }

            if (werewolf.isAlive()) {
                werewolf.attack(vlad);
            }

        }


        Backpack backpack = new Backpack();

        backpack.addItem("small medkit");
        backpack.addItem("big medkit");
        backpack.addItem("small medkit");
        backpack.printItems();
    }
}

//TODO: Hero money, Creature gives money after defeat(longer battle = more money?)
//TODO: Do smth if Creature is dead (disability to perform on It attacks, or It cant perform attacks)

//TODO: Dealer to buy levelUp, medkits, shield.

