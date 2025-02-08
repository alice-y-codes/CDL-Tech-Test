package org.cdlkata.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Checkout {
    private int total = 0;
    private PriceModifier[] priceModifiers;

    public Checkout(PriceModifier[] priceModifiers) {
        this.priceModifiers = priceModifiers;
    }

    public void scan(char item) {
        if (item == 'A') {}
        total += 50;
    }

}
