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
            int price = priceModifier.getPrice();
            int discountedPrice = priceModifier.getDiscountedPrice();
            int discountedQuantity = priceModifier.getDiscountedQuantity();
            Discount discount = priceModifier.getDiscount() != null ? priceModifier.getDiscount() : new NoDiscount();

            return discount.calculatePrice(quantity, price, discountedPrice, discountedQuantity);
        }

        return 0;
    }
}
