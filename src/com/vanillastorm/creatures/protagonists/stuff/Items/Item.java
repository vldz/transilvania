package com.vanillastorm.creatures.protagonists.stuff.Items;

import java.util.HashMap;
import java.util.Map;

public class Item {
//    private int itemId;
//    private int weight;
//
//    public Item(int itemId, int weight) {
//        this.itemId = itemId;
//        this.weight = weight;
//    }

    private final static Map<String, Integer> items = new HashMap<String, Integer>() {
        {
//              ("name of item", weight)
             put("small medkit", 1);
             put("medium medkit", 2);
             put("big medkit", 3);
        }
    };



    public static int getWeightOfItem(String name) {
        int weightOfItem = -1;
        if (items.containsKey(name)) {
            weightOfItem = items.get(name);
        }
        return weightOfItem;
    }
}

//TODO: add items objects