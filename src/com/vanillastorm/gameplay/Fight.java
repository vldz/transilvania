package com.vanillastorm.gameplay;

import com.vanillastorm.creatures.Creature;
import com.vanillastorm.creatures.protagonists.stuff.Backpack;
import com.vanillastorm.util.Color;

import java.util.Scanner;

public class Fight {
    private Creature hero;
    private Creature villain;

    private Scanner scanner = new Scanner(System.in);

    public Fight(Creature hero, Creature villain) {
        this.hero = hero;
        this.villain = villain;

        fight();
    }

    private void fight() {
        while (bothAlive(this.hero, this.villain)) {
            if (hero.isAlive()) {
                heroTurn();
            }
            if (villain.isAlive()) {
                villainTurn();
            }
        }
    }

    private boolean bothAlive(Creature hero, Creature villain) {
        if (hero.isAlive() && villain.isAlive()) {
            return true;
        } else return false;
    }

    private void heroTurn() {
        boolean performedAction = false;

        while (!performedAction) {
            printSuggestedAction();
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    hero.attack(villain);
                    performedAction = true;
                    break;
                case 2:
                    Backpack.printItems();
                    printBackpackActions();
                    boolean wasUsed = false;
                    while (!wasUsed) {
                        int backpackAction = scanner.nextInt();
                        if (backpackAction <= Backpack.getSize()) {
                            hero.useMedkit(Backpack.getItemName(backpackAction - 1));
                            wasUsed = true;
                            performedAction = true;
                            break;
                        } else if (backpackAction == Backpack.getSize() + 1){
                            performedAction = false;
                            break;
                        } else {
                            System.out.println("No such option, please, try again");
                        }
                    }

                case 0:
                    break;
            }
        }
    }

    private void villainTurn() {
        System.out.println();
        villain.attack(hero);
    }

    private void printSuggestedAction() {
        System.out.println(Color.WHITE + "Chose an action:");

        System.out.println("1.Simple attack");
        //System.out.println("2.Strong attack attack");
        System.out.println("2.Check whats in the backpack and use it");

        System.out.println("0.Skip move");
    }

    private void printBackpackActions() {
        for (int i = 1; i <= Backpack.getSize(); i++) {
            System.out.println(i + ".Use " + Backpack.getItemName(i - 1));
        }
        System.out.println(Backpack.getSize() + 1 + ".Go back");
    }
}
