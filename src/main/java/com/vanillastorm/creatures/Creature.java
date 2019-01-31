package com.vanillastorm.creatures;

import com.vanillastorm.creatures.stuff.Backpack;
import com.vanillastorm.creatures.stuff.Items.Item;
import com.vanillastorm.creatures.stuff.Items.medkits.Medkit;
import com.vanillastorm.creatures.stuff.Shield;
import com.vanillastorm.util.Color;

public class Creature implements Action {

    private String name;
    private int maxHp;
    private int hp;

    private int level;

    private double strength;
    private int accuracy;

    private double maxDefencePoints;
    private double defencePoints;

    private int gold;

    private String color;

    private String damageColor = Color.RED;
    private String hpColor = Color.GREEN;

    public Creature() {
        this.hp = maxHp;
    }

//    public Creature(String name, int hp, int level, double strength, int accuracy, int gold, String color) {
//        this.name = name;
//
//        this.defencePoints = maxDefencePoints;
//
//        this.hp = hp;
//        this.maxHp = this.hp;
//        this.level = level;
//
//        this.strength = strength;
//        this.accuracy = accuracy;
//
//        this.gold = gold;
//
//        this.color = color;
//    }

    @Override
    public void attack(Creature creature) {
        int damage = (int) (((this.strength * generateAccuracy())) * (1 - (creature.defencePoints / 150)));
        printInfoDamage(creature, damage);
        creature.takeDamage(damage);
        if (!creature.isAlive()) {
            this.getEnemysGold(creature);
            System.out.println(this.getName() + " earns " + Color.YELLOW + "+" + creature.getGold() + " gold(" + Color.YELLOW + this.getGold() + ").");
        }
        System.out.println();
    }

    @Override
    public void takeDamage(double damage) {

        this.hp -= (int) damage;
        // TODO: defencePoints formula
        this.defencePoints -= (int) damage; // ???

        if (this.defencePoints <= 0) {
            this.defencePoints = 0;
            System.out.printf("\nShield of %s is broken.", this.getName());
            // how to see this once?
        }

        if (isAlive()) {
            System.out.printf("%n%s is now %d hp.\n", this.name, this.hp);
        } else {
            System.out.format("%n%s is dead.\n", this.name);
        }
    }

    @Override
    public void heal(Creature creature, Medkit medkit) {
        if (this.hp != this.maxHp) {
            int totalHP = this.hp += medkit.getHealPoints();
            this.hp = (totalHP > maxHp) ? maxHp : totalHP;
            Backpack.remove(medkit);
            printHealUsage(medkit);
        } else {
            System.out.println("No need to heal, " + creature.getName() + " is full hp.");
        }
    }

    //TODO: make usage of different items

    public void useItem(Item item) {
        if (item instanceof Medkit) {
            heal(this, (Medkit) item);
        }
    }

    @Override
    public double generateAccuracy() {
        double totalAccuracy = ((Math.random() * 100)) + this.accuracy;
        return totalAccuracy / 100;
//        System.out.print(Color.ANSI_RESET + "");
//        if (totalAccuracy < 15) {
//            System.out.println("Pussy attack. ");
//            return 0.1;
//        } else if (totalAccuracy >= 15 && totalAccuracy < 30) {
//            System.out.println("Weak attack. ");
//            return 0.25;
//        } else if (totalAccuracy >= 30 && totalAccuracy < 65) {
//            System.out.println("O.K. attack. ");
//            return 0.5;
//        } else if (totalAccuracy >= 65 && totalAccuracy < 90) {
//            System.out.println("Noooooice attack. ");
//            return 0.75;
//        } else {
//            System.out.println("IN YOUR FACE.");
//            return 0.99;
//        }
    }

    public void printInfoDamage(Creature anotherCreature, int damage) {
        System.out.printf(
                        this.name + " done " +
                        "-" + damage
                        + " damage to "
                        + anotherCreature.getName()
                        + "(" + anotherCreature.getHp() + " hp, " + (int) anotherCreature.defencePoints + " shield).");
    }

    public void printHealUsage(Medkit medkit) {
        System.out.println(
                        this.name + " heals " + "+" + medkit.getHealPoints() + " hp " +
                        "with a " + medkit.getName() + ".");
        System.out.println(
                this.name + " is now " + this.hp + " hp."
        );
    }

    @Override
    public boolean isAlive() {
        return this.hp > 0;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getGold() {
        return gold;
    }

    public int getEnemysGold(Creature creature) {
        this.gold += creature.getGold();
        return this.gold;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setMaxDefencePointsByShieldName(String shieldName) {
        double def = Shield.getMaxDefencePoints(shieldName);
        this.maxDefencePoints = def;
        this.defencePoints = def;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}

//TODO: poison, bleed effects(steps), mana