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
    private String shieldName;

    public String attack(Character character) {
        String m = "";
        int fullDamage = damageCulculation(character, 0);
        int damageWithArmour = (int) (fullDamage * (1 - (character.defencePoints / 150)));

        m += printInfoDamage(character, damageWithArmour);

        m += character.takeDamage(character, fullDamage);
        return m;
    }

    // mana check
    public String attackWithWeapon(Character character) {
        String m = "";
        int manaCulc = this.mana - this.weapon.getMinusManaAfterUsage();
        if (manaCulc >= 0) {
            int fullDamageWithWeapon = damageCulculation(character, 1) + this.weapon.getDamage();
            int damageWithArmour = (int) (fullDamageWithWeapon * (1 - (character.defencePoints / 150)));
            this.mana -= this.weapon.getMinusManaAfterUsage();

            m += printInfoDamage(character, damageWithArmour, this.weapon.getWeaponName());

            m += character.takeDamage(character, fullDamageWithWeapon);
        } else {
            m = "Not enough mana, chiiil, dude.";
        }
        return m;
    }

    public void superStrongAttack(Character character) {

    }

    private int damageCulculation(Character character, int weaponCoef) {
        return (int) ((this.strength * generateAccuracy(weaponCoef)));
    }

    private String takeDamage(Character character, int damage) {
        String m = "";
//        System.out.println(character.getName() + ": ");
//        System.out.println("full damage "+ damage);
        int hpDamage = (int) (damage * (1 - (character.defencePoints / 150)));
//        System.out.println("hpDamage " + hpDamage);
        int armourDamage = damage - hpDamage ;
//        System.out.println("-----------------");
//        System.out.println("armourDamage " + armourDamage);

        this.hp -= hpDamage;
        this.defencePoints -= armourDamage;

        if (isAlive()) {
            m = "\n-----------------------------------------";
            m += "\n" + this.name + ": " + this.hp + " hp, " + this.mana + " mana";
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
        return character.getName() + " is downed and looted. \n" + this.getName() + " earns " + "+" + character.getGold() + " gold(" + this.getGold() + ").";
    }

    public void heal(Character character, Medkit medkit) {
        if (this.hp != this.maxHp) {
            int totalHP = this.hp + medkit.getHealPoints();
            this.hp = (totalHP > maxHp) ? maxHp : totalHP;
            Backpack.remove(medkit);
            printHealUsage(medkit);
        } else {
            System.out.println("No need to heal, " + character.getName() + " is full hp.");
        }
    }

    public String restoreMana(int manaAmount) {
        int totalMana = this.mana + manaAmount;
        if (totalMana >= this.maxMana) {
            this.mana = this.maxMana;
            return "Mana is full(" + this.mana + ").";
        } else {
            this.mana = totalMana;
            return "Mana restored for " + manaAmount + "(" + this.mana + ").";
        }
    }

    public String healVillaine(int amount) {
        String m = "";
        if (this.hp != this.maxHp) {
            int totalHP = this.hp + amount;
            this.hp = (totalHP > maxHp) ? maxHp : totalHP;
            m = this.getName() + " heals " + amount + " hp(" + this.getHp() + ").";
        } else {
            m = "No heal";
        }
        return m;
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
            return totalAccuracy / 80;
        } else {
            return totalAccuracy / 100;
        }
    }

    public String printInfoDamage(Character anotherCharacter, int damage) {
        return
                this.name + " done "
                        + damage
                        + " damage.";
    }

    public String printInfoDamage(Character anotherCharacter, int damage, String weaponName) {
        return
                this.name + " done "
                        + damage
                        + " damage with "
                        + weaponName.toUpperCase() + ".";
    }

    public void printHealUsage(Medkit medkit) {
        System.out.println(
                this.name + " heals " + "+" + medkit.getHealPoints() + " hp " +
                        "with a " + medkit.getName() + ".");
        System.out.println(
                this.name + " is now " + this.hp + " hp."
        );
    }

    public String character() {
        String characterInfo = "---Character INFO---\n";
        characterInfo += "Name: " + this.getName() + "\n";
        characterInfo += "Shield: " + this.getShieldName() + "(" + this.getDefencePoints() + ")" + "\n";
        characterInfo += "Hp: " + this.getHp() + "\n";
        characterInfo += "Mana: " + this.getMana() + "\n";
        characterInfo += "Weapon: " + this.weaponName() + "\n";
        characterInfo += "Gold: " + this.getGold() + "\n";
        characterInfo += "---Character INFO---\n";
        return characterInfo;
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
        this.shieldName = shieldName;
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

    public String getMana() {
        return "" + this.mana;
    }

    public String getShieldName() {
        return this.shieldName;
    }

    public String getDefencePoints() {
        return "" + this.defencePoints;
    }

    public int getLevel() {
        return this.level;
    }
}

//TODO: poison, bleed effects(steps), mana