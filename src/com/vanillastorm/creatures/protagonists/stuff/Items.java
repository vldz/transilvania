package com.vanillastorm.creatures.protagonists.stuff;

import java.util.HashMap;
import java.util.Map;

public class Items {
    private final static Map<String, Integer> items = new HashMap<String, Integer>() {
        {
             put("small medkit", 1);
             put("medium medkit", 2);
             put("big medkit", 3);
        }
    };

    public static int getWeithtOfItem(String name) {
        int weightOfItem = -1;
        if (items.containsKey(name)) {
            weightOfItem = items.get(name);
        }
        return weightOfItem;
    }
}

//TODO: add items