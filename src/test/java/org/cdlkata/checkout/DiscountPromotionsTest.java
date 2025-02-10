package org.cdlkata.checkout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountPromotionsTest {

    @Test
    void shouldCalculatePriceForMultiBuyDiscount() {
        MultiBuyDiscount multiBuyDiscount = new MultiBuyDiscount();

        int price = multiBuyDiscount.calculatePrice(3, 50, 130, 3);

        assertTrue(multiBuyDiscount.isDiscountApplicable(3, 50));
        assertEquals(130, price);
    }

    @Test
    void shouldCalculatePriceForNoDiscount() {
        NoDiscount noDiscount = new NoDiscount();

        int price = noDiscount.calculatePrice(3, 50, 130, 3);

        assertFalse(noDiscount.isDiscountApplicable(3, 3));
        assertEquals(150, price);
    }
}
