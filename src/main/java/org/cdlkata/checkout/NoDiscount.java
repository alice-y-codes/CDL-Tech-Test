package org.cdlkata.checkout;

public class NoDiscount implements Discount {
    @Override
    public int calculatePrice(int quantity, int price, int discountedPrice, int discountedQuantity) {
        return quantity * price;
    }

    @Override
    public boolean isDiscountApplicable(int discountedQuantity, int discountedPrice) {
        return false;
    }
}

