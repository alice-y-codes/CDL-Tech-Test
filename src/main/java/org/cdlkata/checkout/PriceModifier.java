package org.cdlkata.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PriceModifier {
    private final int price;
    private final int discountedPrice;
    private final int discountedQuantity;

    public int getPriceForQuantity(int quantity) {
        if (isDiscountApplicable()) {
            return calculateDiscountedPrice(quantity);
        }
        return calculateRegularPrice(quantity);
    }

    public boolean isDiscountApplicable() {
        return (discountedQuantity > 1 && discountedPrice > 0 && price > 0);
    }

    private int calculateDiscountedPrice(int quantity) {
        int fullDiscountSets = quantity / discountedQuantity;
        int remainingQuantity = quantity % discountedQuantity;
        return (fullDiscountSets * discountedPrice) + (remainingQuantity * price);
    }

    private int calculateRegularPrice(int quantity) {
        return quantity * price;
    }
}
