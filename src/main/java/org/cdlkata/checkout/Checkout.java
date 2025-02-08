package org.cdlkata.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Checkout {
    private final PriceCalculator priceCalculator;
    private final Basket basket;

    public void scan(String itemName) {
        basket.addItem(itemName);

    }

    public int getTotal() {
        return priceCalculator.calculateTotal(basket);
    }
}
