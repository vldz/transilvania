package com.vanillastorm.creatures.stuff;

import java.util.ArrayList;
import java.util.List;

public class Weapon {
    private String weaponName;
    private int minusManaAfterUsage;
    private int damage;

    private final static List<Weapon> weapons = new ArrayList<Weapon>() {
        {
            add(new Weapon("Knife", 3, 7));
            add(new Weapon("Katana", 5, 10));
            add(new Weapon("Nunchacks", 2, 4));
        }
    };

    private Weapon(String weaponName, int minusManaAfterUsage, int damage) {
        this.weaponName = weaponName;
        this.minusManaAfterUsage = minusManaAfterUsage;
        this.damage = damage;
    }

    public static Weapon getWeapon (String weaponName) {
        for (int i = 0; i < weapons.size(); i++) {
            if (weapons.get(i).getWeaponName().equals(weaponName)) {
                return weapons.get(i);
            }
        }
        System.out.println("No " + weaponName + ".");
        return null;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getDamage() {
        return damage;
    }

    public int getMinusManaAfterUsage() {
        return minusManaAfterUsage;
    }

    // - weapon list
}
