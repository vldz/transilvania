package com.vanillastorm.creatures;

import com.vanillastorm.util.Color;

public class Creature implements Action {

    private String name;
    private int hp;
    private int maxHp;
    private int level;

    private double strength;
    private int accuracy;
    private int defence;

    private String color;

    private String damagedHPColor = Color.RED;
    private String healedHPColor = Color.GREEN;

    public Creature(String name, int hp, int level, double strength, int accuracy, int defence, String color) {
        this.name = name;
        this.defence = defence;

        this.hp = hp;
        this.maxHp = this.hp;
        this.level = level;

        this.strength = strength;
        this.accuracy = accuracy;

        this.color = color;
    }

    @Override
    public void attack(Creature creature) {
        int damage = (int) ((this.level * this.strength * generateAccuracy())) * (1 - (creature.defence / 200));
        printInfoDamage(creature, damage);
        creature.takeDamage(damage);
    }

    @Override
    public void takeDamage(double damage) {

        this.hp -= (int) damage;
        this.defence -= (int) damage / (1 - defence / 200);

        if (this.defence <= 0) {
            this.defence = 0;
            System.out.printf(this.color + "\nShield of %s is broken.", this.getName().toLowerCase());
            // how to see this once?
        }

        if (isAlive()) {
            System.out.printf(this.color + "%n%s is now %s%d hp%s.%n", this.name, healedHPColor, this.hp, this.color);
        } else {
            System.out.format(this.color + "%n%s is dead.\n", this.name);
        }
    }

    @Override
    public void heal(Creature creature, int medkit) {
        int totalHP = this.hp += medkit;
        this.hp = (totalHP > maxHp) ? maxHp : totalHP;

        System.out.printf(this.color + "%n%s %s+%d hp%s with a medkit.", this.name, healedHPColor, medkit, this.color);
    }

    @Override
    public double generateAccuracy() {
        double totalAccuracy = ((Math.random() * 100)) + this.accuracy;
        return totalAccuracy / 100;
//        System.out.print(Color.ANSI_RESET);
//        if (totalAccuracy < 15) {
//            //System.out.println("Weak attack. ");
//            return 0.1;
//        } else if (totalAccuracy >= 15 && totalAccuracy < 30) {
//            //System.out.println("Nice attack. ");
//            return 0.25;
//        } else if (totalAccuracy >= 30 && totalAccuracy < 65) {
//            //System.out.println("OK attack. ");
//            return 0.5;
//        } else if (totalAccuracy >= 65 && totalAccuracy < 90) {
//            //System.out.println("Almost perfect attack. ");
//            return 0.75;
//        } else {
//            //System.out.println("Amazing attack! ");
//            return 0.99;
//        }
    }

    @Override
    public boolean isAlive() {
        return this.hp > 0;
    }

    public void printInfoDamage(Creature anotherCreature, int damage) {
        System.out.printf(
                this.color +
                        this.name + " done " +
                        damagedHPColor + damage +
                        this.color + " damage to " +
                        anotherCreature.color + anotherCreature.getName().toLowerCase() + "(" + anotherCreature.getHp() + " hp, " + anotherCreature.defence + " shield)" +
                        this.color + ".");
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }
}