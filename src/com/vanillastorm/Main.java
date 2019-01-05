package com.vanillastorm;

import com.vanillastorm.creatures.antagonists.Villain;
import com.vanillastorm.creatures.protagonists.Hero;
import com.vanillastorm.creatures.protagonists.characters.Detective;
import com.vanillastorm.creatures.protagonists.stuff.Backpack;
import com.vanillastorm.creatures.protagonists.stuff.Shield;
import com.vanillastorm.util.Color;

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


        Backpack.addItem("small medkit");
        Backpack.addItem("big medkit");
        Backpack.addItem("sword");
        Backpack.printItems();

        vlad.useMedkit("small medkit");
        vlad.useMedkit("big medkit");

    }
}

//TODO: Hero money, Creature gives money after defeat(longer battle = more money?)
//TODO: Do smth if Creature is dead (disability to perform on It attacks, or It cant perform attacks)

//TODO: mana?
//TODO: -defence formula


