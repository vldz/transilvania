package com.vanillastorm.creatures.protagonists.stuff;


import com.vanillastorm.util.Color;

import java.util.HashMap;
import java.util.Map;

public class Backpack {
    Map<String, Integer> itemsInBackpack;
    String color = Color.BLUE;

    //check if is in backpack item
    //get item by name
    //remove item

    public Backpack() {
        this.itemsInBackpack = new HashMap<>();
    }

    public void addItem(String item) {
        Integer current = itemsInBackpack.getOrDefault(item, 0);
        itemsInBackpack.put(item, current + 1);
    }

    public void printItems() {
        int size = this.itemsInBackpack.size();
        String s;
        int maxAmountOfOneTypeItem = 0;

        for (Map.Entry<String, Integer> entry : itemsInBackpack.entrySet()) {
            int currentAmount = entry.getValue();
            if (currentAmount > maxAmountOfOneTypeItem) {
                maxAmountOfOneTypeItem = currentAmount;
            }
        }

        System.out.print(color);
        if (size == 0) {
            System.out.println("There is in backpack 0 items.");
        } else if (size == 1 && maxAmountOfOneTypeItem < 2) {
            System.out.println("There is in backpack: ");
        } else {
            System.out.println("There are in backpack: ");
        }

        for (Map.Entry<String, Integer> entry : itemsInBackpack.entrySet()) {
            s =(entry.getValue() > 1) ? "s" : "";
            System.out.println(entry.getValue() + " " + entry.getKey() + s);
        }
    }
}
