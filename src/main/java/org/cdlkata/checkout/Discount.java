package org.cdlkata.checkout;

public interface Discount {
    int calculatePrice(int quantity, int price, int discountedPrice, int discountedQuantity);
}

