package com.vanillastorm;

import com.vanillastorm.creatures.Creature;
import com.vanillastorm.creatures.HallOfFame;
import com.vanillastorm.creatures.stuff.Backpack;
import com.vanillastorm.creatures.stuff.Items.medkits.BigMedkit;
import com.vanillastorm.creatures.stuff.Items.medkits.Medkit;
import com.vanillastorm.creatures.stuff.Items.medkits.SmallMedkit;

import com.vanillastorm.gameplay.Fight;
import com.vanillastorm.gameplay.Game;

public class Main {

    public static void main(String[] args) {
        HallOfFame hallOfFame = new HallOfFame();
        Creature detective = hallOfFame.getCharacter(0);
        Creature kathy = hallOfFame.getCharacter(3);

        Medkit small = new SmallMedkit();
        Medkit big = new BigMedkit();

        Backpack.addItem(small);
        Backpack.addItem(big);
//        System.out.println("Game initialisation");
//        Game game = new Game();
//        System.out.println("Game initialisation finish");
        Fight fight = new Fight(detective, kathy);
    }

// TODO: Story telling
// TODO: different attacks
// TODO: Telegram bot

}


