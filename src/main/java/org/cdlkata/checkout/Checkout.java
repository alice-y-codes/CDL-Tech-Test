package org.cdlkata.checkout;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Checkout {
    private List<PriceModifier> priceModifiers;
    private List<Character> scannedItems;


    public Checkout(List<PriceModifier> priceModifiers) {
        this.priceModifiers = priceModifiers;
        this.scannedItems = new ArrayList<>();
    }

    public void scan(char item) {
        scannedItems.add(item);

    }

    public int getTotal() {
        int total = 0;

        if (priceModifiers == null) {
            return total;
        }

        for (PriceModifier priceModifier : priceModifiers) {
            long count = scannedItems.stream().filter(item -> item == priceModifier.getItemName()).count();

            if (count >= priceModifier.getSpecialQuantity() && priceModifier.getSpecialQuantity() != 0) {
                total += (int) ((count / priceModifier.getSpecialQuantity()) * priceModifier.getSpecialPrice());
                count %= priceModifier.getSpecialQuantity();
            }

            total += (int) (count * priceModifier.getPrice());
        }

        return total;
    }
}
