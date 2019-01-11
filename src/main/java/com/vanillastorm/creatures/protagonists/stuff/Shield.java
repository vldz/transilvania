package com.vanillastorm.creatures.protagonists.stuff;

import java.util.HashMap;
import java.util.Map;

public class Shield {
    private final static Map<String, Double> shields = new HashMap<String, Double>() {
        {
            put("None", 0.0);
            put("Lab coat", 8.5);
            put("Kimono", 8.5);
            put("Wooden", 10.0);
            put("Knight armour", 50.0);
            put("Kevlar", 90.0);
            put("Kevlar and Helmet", 100.0);
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