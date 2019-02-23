package com.vanillastorm;

import com.vanillastorm.creatures.Character;
import com.vanillastorm.creatures.HallOfFame;
import com.vanillastorm.creatures.stuff.Backpack;
import com.vanillastorm.creatures.stuff.Items.medkits.BigMedkit;
import com.vanillastorm.creatures.stuff.Items.medkits.Medkit;
import com.vanillastorm.creatures.stuff.Items.medkits.SmallMedkit;

import com.vanillastorm.gameplay.BattleField;
import com.vanillastorm.gameplay.Game;

public class Main {

    public static void main(String[] args) {
        Medkit small = new SmallMedkit();
        Medkit big = new BigMedkit();

        Backpack.addItem(small);
        Backpack.addItem(big);
        System.out.println("Game initialisation");
        Game game = new Game();
        System.out.println("Game initialisation finish");
        //BattleField fight = new BattleField(detective, kathy);
    }

// TODO: different attacks

}


