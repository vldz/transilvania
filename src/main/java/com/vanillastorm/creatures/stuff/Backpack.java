package com.vanillastorm.creatures.stuff;


import com.vanillastorm.creatures.stuff.Items.Item;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Backpack {
    private Map<Item, Integer> itemsInBackpack = new LinkedHashMap<>();
    private List<String> itemNames = new ArrayList<>();

    private int maxSizeOfBackpack = 10;
    private int currentSize = 0;

    public void addItem(Item item) {
        int weight = item.getWeight();
        if (isEnoughSpace(weight)) {
            Integer current = itemsInBackpack.getOrDefault(item, 0);
            itemsInBackpack.put(item, current + 1);
            itemNames.add(item.getName());
        } else {
            System.out.println("Cannot add " + item.getName() + "(" + weight + "), backpack is full.");
        }
    }

    public String printItems() {
        int size = getSize();
        String s;
        String m = "";
        int maxAmountOfOneTypeItem = 0;

        for (Map.Entry<Item, Integer> entry : itemsInBackpack.entrySet()) {
            int currentAmount = entry.getValue();
            if (currentAmount > maxAmountOfOneTypeItem) {
                maxAmountOfOneTypeItem = currentAmount;
            }
        }

        if (size == 0) {
            m += "In backpack 0 items.";
        } else if (size == 1 && maxAmountOfOneTypeItem < 2) {
            m += "There is in backpack: ";
        } else {
            m += "There are in backpack: ";
        }
        m += "\n-----------------------";

        for (Map.Entry<Item, Integer> entry : itemsInBackpack.entrySet()) {
            s = (entry.getValue() > 1) ? "s" : "";
            m += "\n* " + entry.getValue() + " " + entry.getKey().getName() + s + "(" + entry.getValue() * entry.getKey().getWeight() + ")";
        }
        m += "\n-----------------------";
        m += "\nWeight curr(" + currentSize + "), max(" + maxSizeOfBackpack + ")\n";

        return m;
    }

    public void remove(Item item) {
        int weight = item.getWeight();
        Integer currentAmount = itemsInBackpack.get(item);
        if (currentAmount != 1) {
            itemsInBackpack.put(item, currentAmount - 1);
            currentSize -= weight;
        } else itemsInBackpack.remove(item);
    }

    private boolean isEnoughSpace(int weightOfItem) {
        int resultSize = currentSize + weightOfItem;
        if (resultSize <= maxSizeOfBackpack) {
            currentSize = resultSize;
            return true;
        } else {
            return false;
        }
    }

    public String getItem(int i) {
        return this.itemNames.get(i);
    }

    public int getSize() {
        return itemsInBackpack.size();
    }
}
