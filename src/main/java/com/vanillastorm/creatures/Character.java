package com.vanillastorm.creatures;

import com.vanillastorm.creatures.stuff.Backpack;
import com.vanillastorm.creatures.stuff.Items.Item;
import com.vanillastorm.creatures.stuff.Items.medkits.Medkit;
import com.vanillastorm.creatures.stuff.Shield;
import com.vanillastorm.creatures.stuff.Weapon;

public class Character {

    private String name;
    private int maxHp;
    private int hp;

    private int maxMana;
    private int mana;

    private int level;

    private double strength;
    private int accuracy;

    private double maxDefencePoints;
    private double defencePoints;

    private Weapon weapon;

    private int gold;

    public String attack(Character character) {
        String m = "";
        int damage = damageCulculation(character, 0);
        m += printInfoDamage(character, damage);
        m += character.takeDamage(damage);
        return m;
    }

    // mana check
    public String attackWithWeapon(Character character) {
        String m = "";
        int damageWithWeapon = damageCulculation(character, 1) + this.weapon.getDamage();
        this.mana -= this.weapon.getMinusManaAfterUsage();
        m += printInfoDamage(character, damageWithWeapon, this.weapon.getWeaponName());
        m += character.takeDamage(damageWithWeapon);
        return m;
    }

    public void superStrongAttack(Character character) {

    }

    private int damageCulculation(Character character, int weaponCoef) {
        return (int) (((this.strength * generateAccuracy(weaponCoef))) * (1 - (character.defencePoints / 300)));
    }

    private String takeDamage(double damage) {
        String m = "";
        this.hp -= (int) damage;
        // TODO: defencePoints formula
        this.defencePoints -= (int) damage;

        if (isAlive()) {
            m += "\n" + this.name + " is now " + this.hp + " hp";
            if (this.defencePoints <= 0) {
                this.defencePoints = 0;
                m += ", no shield.";
            } else {
                m += ", " + (int) this.defencePoints + " shield.";
            }
        } else {
            m += "\n" + this.name + " is dead.\n";
        }
        return m;
    }

    public String killed(Character character) {
        this.getEnemysGold(character);
        return this.getName() + " earns " + "+" + character.getGold() + " gold(" + this.getGold() + ").";
    }

    public void heal(Character character, Medkit medkit) {
        if (this.hp != this.maxHp) {
            int totalHP = this.hp += medkit.getHealPoints();
            this.hp = (totalHP > maxHp) ? maxHp : totalHP;
            Backpack.remove(medkit);
            printHealUsage(medkit);
        } else {
            System.out.println("No need to heal, " + character.getName() + " is full hp.");
        }
    }

    //TODO: make usage of different items
    public void useItem(Item item) {
        if (item instanceof Medkit) {
            heal(this, (Medkit) item);
        }
    }

    public double generateAccuracy(int weaponCoef) {
        double totalAccuracy = ((Math.random() * 100)) + this.accuracy;
        // 1 for attacks with weapon
        if (weaponCoef == 1) {
            return totalAccuracy / 50;
        } else {
            return totalAccuracy / 100;
        }
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

    public String printInfoDamage(Character anotherCharacter, int damage) {
        return
                this.name + " done " +
                        "-" + damage
                        + " damage to "
                        + anotherCharacter.getName()
                        + "(" + anotherCharacter.getHp() + " hp, " + (int) anotherCharacter.defencePoints + " shield).";
    }

    public String printInfoDamage(Character anotherCharacter, int damage, String weaponName) {
        return
                this.name + " done with "
                        + weaponName.toLowerCase()
                        + " -" + damage
                        + " damage to "
                        + anotherCharacter.getName()
                        + "(" + anotherCharacter.getHp() + " hp, " + (int) anotherCharacter.defencePoints + " shield).";
    }

    public void printHealUsage(Medkit medkit) {
        System.out.println(
                this.name + " heals " + "+" + medkit.getHealPoints() + " hp " +
                        "with a " + medkit.getName() + ".");
        System.out.println(
                this.name + " is now " + this.hp + " hp."
        );
    }

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

    public int getEnemysGold(Character character) {
        this.gold += character.getGold();
        return this.gold;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
        this.mana = maxMana;
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

    public void setWeapon(String weapon) {
        this.weapon = Weapon.getWeapon(weapon);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String weaponMana(){
        return "" + this.weapon.getMinusManaAfterUsage();
    }

    public String weaponName() {
        return "" + this.weapon.getWeaponName();
    }
}

//TODO: poison, bleed effects(steps), mana