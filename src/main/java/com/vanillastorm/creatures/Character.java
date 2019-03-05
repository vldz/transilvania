package com.vanillastorm.creatures;

import com.vanillastorm.creatures.stuff.Backpack;
import com.vanillastorm.creatures.stuff.Items.Item;
import com.vanillastorm.creatures.stuff.Items.Items;
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

    private Backpack backpack;

    public Character() {
        this.backpack = new Backpack();
        backpack.addItem(Items.getItem(0));
        backpack.addItem(Items.getItem(0));
        backpack.addItem(Items.getItem(1));
        backpack.addItem(Items.getItem(2));
        backpack.addItem(Items.getItem(3));
    }

    public String attack(Character character) {
        String m = "";
        int fullDamage = damageCalculation(character, 0);
        int damageWithArmour = (int) (fullDamage * (1 - (character.defencePoints / 150)));

        m += printInfoDamage(damageWithArmour);

        m += character.takeDamage(character, fullDamage);
        return m;
    }

    public String attackWithWeapon(Character character) {
        String m = "";
        int manaCalc = this.mana - this.weapon.getMinusManaAfterUsage();
        if (manaCalc >= 0) {
            int fullDamageWithWeapon = damageCalculation(character, 1) + this.weapon.getDamage();
            int damageWithArmour = (int) (fullDamageWithWeapon * (1 - (character.defencePoints / 150)));
            this.mana -= this.weapon.getMinusManaAfterUsage();

            m += printInfoDamage(damageWithArmour, this.weapon.getWeaponName());

            m += character.takeDamage(character, fullDamageWithWeapon);
        } else {
            m = "Not enough mana, chiiil, dude.";
        }
        return m;
    }

    public void superStrongAttack(Character character) {

    }

    private int damageCalculation(Character character, int weaponCoef) {
        return (int) ((this.strength * generateAccuracy(weaponCoef)));
    }

    private String takeDamage(Character character, int damage) {
        String m = "";
        int hpDamage = (int) (damage * (1 - (character.defencePoints / 150)));
        int armourDamage = damage - hpDamage ;

        this.hp -= hpDamage;
        this.defencePoints -= armourDamage;

        m = infoAfterAttack();
        return m;
    }

    private String infoAfterAttack() {
        String m = "";
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

    public String heal(Item medkit) {
        if (this.hp != this.maxHp) {
            int totalHP = this.hp + medkit.getImpactPoints();
            this.hp = (totalHP > maxHp) ? maxHp : totalHP;
            this.backpack.remove(medkit);
            String m = printHealUsage(medkit);
            m += infoAfterAttack();
            return m;
        } else {
            return "No need to heal, chiiil.";
        }
    }

    public String restoreMana(Item manaItem) {
        if (this.mana != this.maxMana) {
            int totalMana = this.mana + manaItem.getImpactPoints();
            this.mana = (totalMana > maxMana) ? maxMana : totalMana;
            this.backpack.remove(manaItem);
            return this.name + " restores " + "+" + manaItem.getImpactPoints() + " mp with a " + manaItem.getName() + "."
                    + infoAfterAttack();
        } else {
            return "No need to mana.";
        }
    }

    public String restoreMana(int manaAmount) {
        String m = "";
        if (this.mana != this.maxMana) {
            int totalMana = this.mana +manaAmount;
            this.mana = (totalMana > maxMana) ? maxMana : totalMana;
            m = this.name + " restores " + "+" + manaAmount + " mp" + "." + infoAfterAttack();
            return m;
        } else {
            return "No need to mana.";
        }
    }

    public String healVillaine(int amount) {
        String m = "";
        if (this.hp != this.maxHp) {
            int totalHP = this.hp + amount;
            this.hp = (totalHP > maxHp) ? maxHp : totalHP;
            m = this.getName() + " heals " + amount + " hp.";
            m += infoAfterAttack();
        } else {
            m = "No heal.";
        }
        return m;
    }

    //TODO: make usage of different items
    public String useItem(String item) {
        if (Items.getItem(item).isMedicine()) {
            return this.heal(Items.getItem(item));
        } else if(Items.getItem(item).isManaBooster()) {
            return this.restoreMana(Items.getItem(item));
        } else return "";
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

    public String printInfoDamage(int damage) {
        return this.name + " done "
                        + damage
                        + " damage.";
    }

    public String printInfoDamage(int damage, String weaponName) {
        return this.name + " done "
                        + damage
                        + " damage with "
                        + weaponName.toUpperCase() + ".";
    }

    public String printHealUsage(Item medkit) {
        return this.name + " heals " + "+" + medkit.getImpactPoints() + " hp" + ".";

    }

    public String character() {
        return "---Character INFO---\n" +
                "Name: " + this.getName() + "\n" +
                "Shield: " + this.getShieldName() + "(" + this.getDefencePoints() + ")" + "\n" +
                "Hp: " + this.getHp() + "\n" +
                "Mana: " + this.getMana() + "\n" +
                "Weapon: " + this.weaponName() + "\n" +
                "Gold: " + this.getGold() + "\n" +
                "---Character INFO---\n";
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

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setDefencePoints(double defencePoints) {
        this.defencePoints = defencePoints;
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

    public int getMaxMana() {
        return maxMana;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getStrength() {
        return strength;
    }

    public int getAccuracy() {
        return accuracy;
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

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void saveBackpack() {
        this.backpack.saveItemsInBackPack();;
    }

    public Weapon getWeapon() {
        return weapon;
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

    public double getDefenceP() {
        return defencePoints;
    }

    public String getDefencePoints() {
        return "" + this.defencePoints;
    }

    public int getLevel() {
        return this.level;
    }

    public String printBackpackItems() {
        return this.backpack.printItems();
    }

    public int amountOfItemsInBackpack() {
        return this.backpack.getSize();
    }

    public String getItemName(int i) {
        return this.backpack.getItem(i);
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setShieldName(String shieldName) {
        this.shieldName = shieldName;
    }

    public void loadBackpack() {
        this.backpack.setItemNames(this.backpack.getSavedItemNames());
        this.backpack.setItemsInBackpack(this.backpack.getSavedItems());
    }
}

//TODO: poison, bleed effects(steps), mana