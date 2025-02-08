package org.cdlkata.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PriceModifier {
    private final char itemName;
    private final int price;
    private final int specialPrice;
    private final int specialQuantity;
}
