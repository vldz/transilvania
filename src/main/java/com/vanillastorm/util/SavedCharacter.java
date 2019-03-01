package com.vanillastorm.util;

import com.vanillastorm.creatures.Character;
import com.vanillastorm.creatures.stuff.Backpack;
import com.vanillastorm.creatures.stuff.Weapon;

public class SavedCharacter {
    private String name;
    private int hp;
    private int mana;
    private int level;
    private double strength;
    private int accuracy;
    private double defencePoints;
    private Weapon weapon;
    private int gold;
    private String shieldName;
    private Backpack backpack;

    public void saveCharacter(Character character) {
        this.name = character.getName();
        this.hp = character.getHp();
        this.mana = character.getMaxMana();
        this.level = character.getLevel();
        this.strength = character.getStrength();
        this.accuracy = character.getAccuracy();
        this.defencePoints = character.getDefenceP();
        this.weapon = character.getWeapon();
        this.gold = character.getGold();
        this.shieldName = character.getShieldName();
        this.backpack = character.getBackpack();
    }

    public void loadCharacter(Character character) {
        character.setName(this.name);
        character.setHp(this.hp);
        character.setMana(this.mana);
        character.setLevel(this.level);
        character.setStrength(this.strength);
        character.setAccuracy(this.accuracy);
        character.setDefencePoints(this.defencePoints);
        character.setWeapon(this.weapon);
        character.setGold(this.gold);
        character.setShieldName(this.shieldName);
        character.setBackpack(this.backpack);
    }
}
