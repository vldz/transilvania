package com.vanillastorm.creatures.protagonists;

import com.vanillastorm.creatures.Creature;
import com.vanillastorm.creatures.protagonists.stuff.Backpack;
import com.vanillastorm.util.Color;

public class Hero extends Creature {
    private int gold;

    public Hero(String name, int hp, int level, double strength, int accuracy, String shield, int gold) {
        super(name, hp, level, strength, accuracy, shield, gold, Color.CYAN);
    }

    @Override
    public int getHp() {
        return super.getHp();
    }

    public void useMedkit (String itemFromBackpack) {
        int healedHP = 0;
        if (Backpack.isInBackpack(itemFromBackpack)) {
            if (this.getHp() != this.getMaxHp()) {
                switch (itemFromBackpack) {
                    case "small medkit":
                        healedHP = 15;
                        break;
                    case "medium medkit":
                        healedHP = 30;
                        break;
                    case "big medkit":
                        healedHP = 50;
                        break;
                }
                heal(this, healedHP);
                printHealUsage(itemFromBackpack, healedHP);

                Backpack.remove(itemFromBackpack);
            } else {
                System.out.println ("No need to heal. You're full hp.");
            }
        }
    }
}

//TODO: add mana to hero, restore mana with alcohol