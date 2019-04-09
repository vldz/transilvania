package com.vanillastorm.creatures.stuff;

import java.util.HashMap;
import java.util.Map;

public class Shield {
    //cant be more then 150 yet
    private final static Map<String, Double> shields = new HashMap<String, Double>() {
        {
            put("None", 0.0);
            put("Lab coat", 8.5);
            put("Kimono", 10.0);
            put("Wooden", 13.0);
            put("Knight armour", 20.0);
            put("Kevlar", 25.0);
            put("Kevlar and Helmet", 39.0);
            put("Heavy armour", 50.0);
        }
    };

    public static double getMaxDefencePoints (String shieldName) {
        if (shields.containsKey(shieldName)) {
            return shields.get(shieldName);
        } else {
            System.out.println("No shield such as " + shieldName);
            return 0;
        }
    }
}