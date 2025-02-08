package org.cdlkata.checkout;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Basket {
    private Map<String, Integer> items;

    public Basket() {
        items = new HashMap<>();
    }

    public void addItem(String itemName) {
        items.put(itemName, items.getOrDefault(itemName, 0) + 1);
    }

    public int getItemQuantity(String itemName) {
        return items.getOrDefault(itemName, 0);
    }
}
