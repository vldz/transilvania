package com.vanillastorm;

import com.vanillastorm.Util.CreatureColor;

public class Creature implements Action {

    private String name;
    private int hp;
    private int maxHp;

    private double strength;
    private double dexterity;

    private int accuracy;

    private int defence;

    private String color;
//    damage hp - RED, healed HP - Green
    private String damagedHPColor = CreatureColor.RED;
    private String healedHPColor = CreatureColor.GREEN;

    public Creature(String name, int hp, double strength, double dexterity, int accuracy, int defence, String color) {
        this.name = name;
        this.defence = defence;

        this.hp = hp;
        this.maxHp = this.hp;

        this.strength = strength;
        this.dexterity = dexterity;
        this.accuracy = accuracy;

        this.color = color;
    }

    @Override
    public void attack(Creature creature) {
        int damage = (int) ((this.strength * this.dexterity * generateAccuracy())) * (1 - creature.defence / 100 );

        System.out.printf(this.color + "%s done %s%d%s damage to %s%s(%d hp, %s shield)%s. ", this.name, damagedHPColor, damage, this.color, creature.color, creature.getName().toLowerCase(), creature.getHp(), creature.defence, this.color);
        creature.takeDamege(damage);
    }

    @Override
    public void takeDamege(double damage) {

        this.hp -= (int) damage;
        this.defence = defence * defence/100;

        if (this.defence == 0) {
            System.out.printf(this.color + "\nShield of %s was broken.", this.getName().toLowerCase());
            // how to see this once?
        }

        if (isAlive()) {
            System.out.printf(this.color + "%n%s is now %s%d hp%s.%n", this.name, healedHPColor, this.hp, this.color);
        } else {
            System.out.format(this.color + "%n%s is dead.", this.name);
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
        double totalAccuracy = ( (Math.random() * 100)) + this.accuracy;
        return totalAccuracy/100;
//        System.out.print(CreatureColor.ANSI_RESET);
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

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    //TODO: For Hero make ability to find out have many health points has character
    //TODO: Do smth if Creature is dead (disability to perform on It attacks, or It cant perform attacks)
    //TODO: is there more colors?
    //TODO: make method to sout info of damage
    //TODO: Dealer to buy levelUp, medkits, shield.
    //TODO: Remake defence 

}