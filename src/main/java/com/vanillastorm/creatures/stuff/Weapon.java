package com.vanillastorm.creatures.stuff;

import java.util.ArrayList;
import java.util.List;

public class Weapon {
    private int weaponTier;
    private String weaponName;
    private int minusManaAfterUsage;
    private int minDamage;

    private final static List<Weapon> weapons = new ArrayList<Weapon>() {
        {
            add(new Weapon(1,"Microscope", 6, 5));
            add(new Weapon(1,"Katana", 5, 10));
            add(new Weapon(1,"Nunchacks", 4, 8));
            add(new Weapon(1,"Cane", 5, 8));
            add(new Weapon(4,"Shocker", 0, 50));
        }
    };

    private Weapon(int weaponTier, String weaponName, int minusManaAfterUsage, int minDamage) {
        this.weaponTier = weaponTier;
        this.weaponName = weaponName;
        this.minusManaAfterUsage = minusManaAfterUsage;
        this.minDamage = minDamage;
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
        return minDamage;
    }

    public int getMinusManaAfterUsage() {
        return minusManaAfterUsage;
    }

    public int getWeaponTier() {
        return weaponTier;
    }
}
