package org.cdlkata.checkout;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class PriceCalculator {
    private final Map<String, PriceModifier> priceModifiers;

    public int calculateTotalPriceForBasket(Basket basket) {
        int total = 0;
        for (Map.Entry<String, Integer> entry : basket.getItems().entrySet()) {
            total += calculatePriceForItem(entry.getKey(), entry.getValue());
        }
        return total;
    }

    private int calculatePriceForItem(String itemName, int quantity) {
        PriceModifier priceModifier = priceModifiers.get(itemName);
        if (priceModifier != null) {
            return priceModifier.getPriceForQuantity(quantity);
        }
        return 0;
    }
}
