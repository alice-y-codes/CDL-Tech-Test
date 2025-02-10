package org.cdlkata.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PriceModifier {
    private final int price;
    private final int discountedPrice;
    private final int discountedQuantity;
    private final Discount discount;
}
