package org.cdlkata.checkout;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class PriceCalculator {
    private final Map<String, PriceModifier> priceModifiers;

    public int calculateTotal(Basket basket) {
        int total = 0;
        for (Map.Entry<String, Integer> entry : basket.getItems().entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            PriceModifier price = priceModifiers.get(itemName);
            if (price != null) {
                total += price.getPriceForQuantity(quantity);
            }
        }
        return total;
    }
}
