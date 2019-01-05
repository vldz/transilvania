package com.vanillastorm.creatures.protagonists.stuff;


import com.vanillastorm.util.Color;

import java.util.LinkedHashMap;
import java.util.Map;

public class Backpack {
    private static Map<String, Integer> itemsInBackpack = new LinkedHashMap<>();;
    private int maxSizeOfBackpack = 5;

    private static String color = Color.BLUE;



    public static void addItem(String item) {
        Integer current = itemsInBackpack.getOrDefault(item, 0);
        itemsInBackpack.put(item, current + 1);
    }

    public static void printItems() {
        int size = itemsInBackpack.size();
        String s;
        int maxAmountOfOneTypeItem = 0;

        for (Map.Entry<String, Integer> entry : itemsInBackpack.entrySet()) {
            int currentAmount = entry.getValue();
            if (currentAmount > maxAmountOfOneTypeItem) {
                maxAmountOfOneTypeItem = currentAmount;
            }
        }

        System.out.println(color + "\n----------------------");
        if (size == 0) {
            System.out.println("In backpack 0 items.");
        } else if (size == 1 && maxAmountOfOneTypeItem < 2) {
            System.out.println("There is in backpack: ");
        } else {
            System.out.println("There are in backpack: ");
        }
        System.out.println("----------------------");

        for (Map.Entry<String, Integer> entry : itemsInBackpack.entrySet()) {
            s = (entry.getValue() > 1) ? "s" : "";
            System.out.println("* " + entry.getValue() + " " + entry.getKey() + s);
        }
        System.out.println("----------------------\n");
    }

    public static boolean isInBackpack(String itemToFind) {
        for (Map.Entry<String, Integer> entry : itemsInBackpack.entrySet()) {
            if (entry.getKey().equals(itemToFind)) {
                if (entry.getValue() > 0) {
                    return true;
                }
            }
        }
        System.out.print(color + "No " + itemToFind + " in backpack.");
        printItems();
        return false;
    }

    public static void remove(String item) {
        Integer current = itemsInBackpack.get(item);
        if (current != 1) {
            itemsInBackpack.put(item, current - 1);
        } else itemsInBackpack.remove(item);
    }

}
//TODO: max size of backpack, weight of the items?