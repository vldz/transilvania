package com.vanillastorm.creatures;

import com.vanillastorm.creatures.protagonists.stuff.Shield;
import com.vanillastorm.util.Color;

public class Creature implements Action {

    private String name;
    private int hp;
    private int maxHp;
    private int level;

    private double strength;
    private int accuracy;

    private double maxDefencePoints;
    private double defencePoints;

    private String color;

    private String damageColor = Color.RED;
    private String hpColor = Color.GREEN;

    public Creature(String name, int hp, int level, double strength, int accuracy, String shieldName, String color) {
        this.name = name;

        this.maxDefencePoints = Shield.getMaxDefencePoints(shieldName);
        this.defencePoints = maxDefencePoints;

        this.hp = hp;
        this.maxHp = this.hp;
        this.level = level;

        this.strength = strength;
        this.accuracy = accuracy;

        this.color = color;
    }

    @Override
    public void attack(Creature creature) {
        int damage = (int) (((this.strength * generateAccuracy())) * (1 - (creature.defencePoints / 150)));
        printInfoDamage(creature, damage);
        creature.takeDamage(damage);
    }

    @Override
    public void takeDamage(double damage) {

        this.hp -= (int) damage;
        // TODO: defencePoints formula
        this.defencePoints -= (int) damage; // ???

        if (this.defencePoints <= 0) {
            this.defencePoints = 0;
            System.out.printf(this.color + "\nShield of %s is broken.", this.getName().toLowerCase());
            // how to see this once?
        }

        if (isAlive()) {
            System.out.printf(this.color + "%n%s is now %s%d hp%s.%n", this.name, hpColor, this.hp, this.color);
        } else {
            System.out.format(this.color + "%n%s is dead.\n", this.name);
        }
    }

    @Override
    public void heal(Creature creature, int medkit) {
        int totalHP = this.hp += medkit;
        this.hp = (totalHP > maxHp) ? maxHp : totalHP;
    }

    @Override
    public double generateAccuracy() {
        double totalAccuracy = ((Math.random() * 100)) + this.accuracy;
        //return totalAccuracy / 100;
        System.out.print(Color.ANSI_RESET + "\n");
        if (totalAccuracy < 15) {
            System.out.println("Pussy attack. ");
            return 0.1;
        } else if (totalAccuracy >= 15 && totalAccuracy < 30) {
            System.out.println("Weak attack. ");
            return 0.25;
        } else if (totalAccuracy >= 30 && totalAccuracy < 65) {
            System.out.println("O.K. attack. ");
            return 0.5;
        } else if (totalAccuracy >= 65 && totalAccuracy < 90) {
            System.out.println("Noooooice attack. ");
            return 0.75;
        } else {
            System.out.println("IN YOUR FACE.");
            return 0.99;
        }
    }

    public void printInfoDamage(Creature anotherCreature, int damage) {
        System.out.printf(
                this.color +
                this.name + " done " +
                        damageColor + "-" + damage +
                this.color + " damage to " +
                anotherCreature.color + anotherCreature.getName().toLowerCase() + "(" + anotherCreature.getHp() + " hp, " + (int) anotherCreature.defencePoints + " shield)" +
                this.color + ".");
    }

    public void printHealUsage(String item, int amountOfHealedHP) {
        System.out.println(
                this.color +
                this.name + " heals " +
                this.hpColor + "+" + amountOfHealedHP + " hp " + this.color +
                "with a " + item + ".");
        System.out.println(
                this.name + " is now " + this.hpColor + this.hp + " hp" + this.color + ".\n"
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

    public int getMaxHp() {
        return maxHp;
    }
}