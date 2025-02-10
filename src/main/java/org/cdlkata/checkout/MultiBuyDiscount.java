package org.cdlkata.checkout;

public class MultiBuyDiscount implements Discount {

    @Override
    public int calculatePrice(int quantity, int price, int discountedPrice, int discountedQuantity) {
        int fullDiscountSets = quantity / discountedQuantity;
        int remainingQuantity = quantity % discountedQuantity;
        return (fullDiscountSets * discountedPrice) + (remainingQuantity * price);
    }
}

