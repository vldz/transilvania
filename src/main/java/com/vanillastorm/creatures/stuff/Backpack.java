package com.vanillastorm.creatures.stuff;


import com.vanillastorm.creatures.stuff.Items.Item;
import com.vanillastorm.creatures.stuff.Items.Items;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Backpack {
    private Map<Item, Integer> itemsInBackpack = new LinkedHashMap<>();
    private List<String> itemNames = new ArrayList<>();

    private Map<Item, Integer> savedItems;
    private List<String> savedItemNames;

    public void addItem(Item item) {
        Integer current = itemsInBackpack.getOrDefault(item, 0);
        itemsInBackpack.put(item, current + 1);
        if (!itemNames.contains(item.getName())) {
            itemNames.add(item.getName());
        }
    }

    public void addItem(Item item, int amount) {
        Integer current = itemsInBackpack.getOrDefault(item, 0);
        itemsInBackpack.put(item, current + amount);
        if (!itemNames.contains(item.getName())) {
            itemNames.add(item.getName());
        }
    }

    public String printItems() {
        int size = itemsInBackpack.size();
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

        String ind = "";
        for (Map.Entry<Item, Integer> entry : itemsInBackpack.entrySet()) {
            if (Items.getItem(entry.getKey().getName()).isMedicine()) {
                ind = "hp";
            } else if (Items.getItem(entry.getKey().getName()).isManaBooster()) {
                ind = "mp";
            }
            s = (entry.getValue() > 1) ? "s" : "";
            m += "\n* " + entry.getValue() + " " + entry.getKey().getName() + s + "(+" + Items.getItem(entry.getKey().getName()).getImpactPoints() + ind + ")";
        }
        m += "\n-----------------------";

        return m;
    }

    public void remove(Item item) {
        Integer currentAmount = itemsInBackpack.get(item);
        if (currentAmount != 1) {
            itemsInBackpack.put(item, currentAmount - 1);
        } else {
            itemsInBackpack.remove(item);
            itemNames.remove(item.getName());
        }
    }

    public String getItem(int i) {
        return this.itemNames.get(i);
    }

    public int getSize() {
        return itemsInBackpack.size();
    }

    public void saveItemsInBackPack() {
        this.savedItems = new LinkedHashMap<>();
        this.savedItemNames = new ArrayList<>();

        this.savedItems.putAll(this.itemsInBackpack);
        this.savedItemNames.addAll(this.itemNames);
    }

    public void setItemsInBackpack(Map<Item, Integer> itemsInBackpack) {
        this.itemsInBackpack = itemsInBackpack;
    }

    public void setItemNames(List<String> itemNames) {
        this.itemNames = itemNames;
    }

    public Map<Item, Integer> getSavedItems() {
        return savedItems;
    }

    public List<String> getSavedItemNames() {
        return savedItemNames;
    }
}
