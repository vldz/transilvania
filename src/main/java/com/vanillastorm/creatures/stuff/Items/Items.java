package com.vanillastorm.creatures.stuff.Items;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private final static List<Item> items = new ArrayList<Item>() {
        {
            add(new Item(0, "Bandage", 15));
            add(new Item(1, "First Aid", 35));
            add(new Item(2, "Medkit",50));

            add(new Item(3,"Beer",  2));
            add(new Item(4,"Tequila", 4));
            add(new Item(5,"Samogon", 6));

        }
    };

    public static Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().toLowerCase().equals(name.toLowerCase())) {
                return item;
            }
        } return null;
    }

    public static Item getItem(int n) {
        return items.get(n);
    }

    public static String getItemImpact(String name) {
        return "" + getItem(name).getImpactPoints();
    }
}
