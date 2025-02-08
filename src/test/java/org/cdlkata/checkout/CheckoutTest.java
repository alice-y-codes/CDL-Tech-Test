package org.cdlkata.checkout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    private Checkout checkout;

    @BeforeEach
    void setUp() {
        checkout = new Checkout();
    }

    @Test
    void shouldStartWithZeroTotal() {
        assertEquals(0, checkout.getTotal());
    }

    @Test
    void shouldCalculateTotalForScannedItems() {

        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('A');

        assertEquals(150, checkout.getTotal());
    }

    @Test
    void shouldApplySpecialPricingForMultipleItems() {
        PriceModifier[] priceModifiers = new PriceModifier[] {
                new PriceModifier('A', 50, 130, 3)
        };

        Checkout checkout = new Checkout(priceModifiers);

        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('A');

        assertEquals(130, checkout.getTotal());
    }

    @Test
    void shouldApplyMultiplePricingRules() {
        PriceModifier[] priceModifiers = new PriceModifier[] {
                new PriceModifier('A', 50, 130, 3),
                new PriceModifier('B', 30, 45, 2),
                new PriceModifier('C', 20, 0, 0),
        };

        Checkout checkout = new Checkout(priceModifiers);

        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('B');
        checkout.scan('B');
        checkout.scan('C');

        assertEquals(195, checkout.getTotal());
    }
}