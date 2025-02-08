package org.cdlkata.checkout;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Checkout {
    private final PriceCalculator priceCalculator;
    private final Basket basket;

    public void scan(String itemName) {
        basket.addItem(itemName);

    }

    public int getTotal() {
        return priceCalculator.calculateTotalPriceForBasket(basket);
    }

}
