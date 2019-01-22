package com.vanillastorm;

import com.vanillastorm.creatures.antagonists.Villain;
import com.vanillastorm.creatures.protagonists.Hero;
import com.vanillastorm.creatures.protagonists.characters.Detective;
import com.vanillastorm.creatures.protagonists.stuff.Backpack;
import com.vanillastorm.creatures.protagonists.stuff.Items.medkits.BigMedkit;
import com.vanillastorm.creatures.protagonists.stuff.Items.medkits.Medkit;
import com.vanillastorm.creatures.protagonists.stuff.Items.medkits.SmallMedkit;
import com.vanillastorm.gameplay.Fight;
import com.vanillastorm.gameplay.Game;

public class Main {

    public static void main(String[] args) {
        Hero vlad = new Detective();
        Villain werewolf = new Villain("Werewolf", 100, 2, 30, 5);


        Medkit small = new SmallMedkit();
        Medkit big = new BigMedkit();

        Backpack.addItem(small);
        Backpack.addItem(big);
        System.out.println("init game");
        Game game = new Game();
        System.out.println("initgame fin");
        //Fight fight = new Fight(vlad, werewolf);
    }

// TODO: Story telling
// TODO: different attacks
// TODO: Telegram bot

}


