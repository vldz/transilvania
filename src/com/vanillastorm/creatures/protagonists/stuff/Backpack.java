package com.vanillastorm.creatures.protagonists.stuff;


import com.vanillastorm.creatures.protagonists.stuff.Items.Item;
import com.vanillastorm.util.Color;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Backpack {
    private static Map<String, Integer> itemsInBackpack = new LinkedHashMap<>();

    private static int maxSizeOfBackpack = 10;
    private static int currentSize = 0;

    private static String color = Color.BLUE;

    public static void addItem(String item) {
        int weight = Item.getWeightOfItem(item);
        if (isEnoughSpace(weight)) {
            Integer current = itemsInBackpack.getOrDefault(item, 0);
            itemsInBackpack.put(item, current + 1);
        } else {
            System.out.println(color + "Cannot add " + item +"(" + weight + "), backpack is full.");
        }
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

        System.out.println(color + "\n-----------------------");
        if (size == 0) {
            System.out.println("In backpack 0 items.");
        } else if (size == 1 && maxAmountOfOneTypeItem < 2) {
            System.out.println("There is in backpack: ");
        } else {
            System.out.println("There are in backpack: ");
        }
        System.out.println("-----------------------");

        for (Map.Entry<String, Integer> entry : itemsInBackpack.entrySet()) {
            s = (entry.getValue() > 1) ? "s" : "";
            System.out.println("* " + entry.getValue() + " " + entry.getKey() + s + "(" + Item.getWeightOfItem(entry.getKey()) * entry.getValue() + ")");
        }
        System.out.println("-----------------------");
        System.out.println("Weight curr(" + currentSize + "), max(" + maxSizeOfBackpack + ")");
        System.out.println("-----------------------\n");
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
        int weight = Item.getWeightOfItem(item);
        Integer currentAmount = itemsInBackpack.get(item);
        if (currentAmount != 1) {
            itemsInBackpack.put(item, currentAmount - 1);
            currentSize -= weight;
        } else itemsInBackpack.remove(item);
    }

    private static boolean isEnoughSpace(int weightOfItem) {
        int resultSize = currentSize + weightOfItem;
        if (resultSize <= maxSizeOfBackpack) {
            currentSize = resultSize;
            return true;
        } else {
            return false;
        }
    }

    public static String getItemName (int number) {
        ArrayList keys = new ArrayList();
        for (Map.Entry<String, Integer> entry : itemsInBackpack.entrySet()) {
            keys.add(entry.getKey());
        }
        String name = (String)keys.get(number);
        return name;
    }

    public static int getSize() {
        return itemsInBackpack.size();
    }
}