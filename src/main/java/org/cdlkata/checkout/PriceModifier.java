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
        if (discountedQuantity != 0 && isDiscountApplicable()) {
            int fullDiscountSets = quantity / discountedQuantity;
            int remainingQuantity = quantity % discountedQuantity;
            return (fullDiscountSets * discountedPrice) + (remainingQuantity * price);
        } else {
            return quantity * price;
        }
    }

    public boolean isDiscountApplicable() {
        return (discountedQuantity > 1 && discountedPrice > 0 && price > 0);
    }

}
