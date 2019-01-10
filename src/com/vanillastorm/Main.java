package com.vanillastorm;

import com.vanillastorm.creatures.antagonists.Villain;
import com.vanillastorm.creatures.protagonists.Hero;
import com.vanillastorm.creatures.protagonists.characters.Detective;
import com.vanillastorm.creatures.protagonists.stuff.Backpack;
import com.vanillastorm.gameplay.Fight;

public class Main {

    public static void main(String[] args) {
        Hero vlad = new Detective();
        Villain werewolf = new Villain("Werewolf", 100, 2, 20, 5);

        Backpack.addItem("small medkit");
        Backpack.addItem("small medkit");
        Backpack.addItem("medium medkit");

        Fight fight = new Fight(vlad, werewolf);

    }

// TODO: Story telling
// TODO: different items,
// TODO: different attacks
// TODO: Telegram bot

}


