package com.vanillastorm.gameplay;

import com.vanillastorm.creatures.Creature;
import com.vanillastorm.creatures.stuff.Backpack;
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
                            hero.useItem(Backpack.getItem(backpackAction - 1));
                            performedAction = true;
                            wasUsed = true;
                        } else if (backpackAction == Backpack.getSize() + 1){
                            performedAction = false;
                            wasUsed = true;
                        } else {
                            System.out.println("No such option, please, try again");
                            wasUsed = false;
                        }
                    }
                    break;
                case 0:
                    performedAction = true;
                    break;
            }
        }
    }

    private void villainTurn() {
        villain.attack(hero);
    }

    private void printSuggestedAction() {
        System.out.println(Color.ANSI_RESET + "Chose an action:");
        System.out.println("-----------------------");

        System.out.println(Color.RED + "1.Simple attack");
        //System.out.println("2.Strong attack attack");
        System.out.println(Color.BLUE + "2.Backpack");

        System.out.println(Color.PURPLE + "0.Skip move");
        System.out.println(Color.ANSI_RESET + "-----------------------");
    }

    private void printBackpackActions() {
        for (int i = 1; i <= Backpack.getSize(); i++) {
            System.out.println(i + ".Use " + Backpack.getItem(i - 1).getName());
        }
        System.out.println(Backpack.getSize() + 1 + ".Go back");
    }
}
