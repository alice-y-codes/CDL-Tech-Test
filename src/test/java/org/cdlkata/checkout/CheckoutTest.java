package org.cdlkata.checkout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    private Checkout checkout;

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldStartWithZeroTotal() {
        checkout = new Checkout();

        assertEquals(0, checkout.getTotal());
    }

    @Test
    void shouldCalculateTotalForScannedItems() {
        checkout = new Checkout(List.of( new PriceModifier('A', 50, 0, 0)));

        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('A');

        assertEquals(150, checkout.getTotal());
    }

    @Test
    void shouldApplySpecialPricingForMultipleItems() {
        List<PriceModifier> priceModifiers = List.of(
                new PriceModifier('A', 50, 130, 3)
        );


        Checkout checkout = new Checkout(priceModifiers);

        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('A');

        assertEquals(130, checkout.getTotal());
    }

    @Test
    void shouldApplyMultiplePricingModifiers() {
        List<PriceModifier> priceModifiers = List.of (
                new PriceModifier('A', 50, 130, 3),
                new PriceModifier('B', 30, 45, 2),
                new PriceModifier('C', 20, 0, 0)
                );

        Checkout checkout = new Checkout(priceModifiers);

        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('B');
        checkout.scan('B');
        checkout.scan('C');

        assertEquals(195, checkout.getTotal());
    }

    @Test
    void shouldHandleUnrecognisedItems() {
        List<PriceModifier> priceModifiers = List.of(new PriceModifier('A', 50, 130, 3));

        Checkout checkout = new Checkout(priceModifiers);

        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('Z');

        assertEquals(130, checkout.getTotal());
    }

    @Test
    void shouldResetCheckoutForNewTransaction() {
        List<PriceModifier> priceModifiers = List.of(new PriceModifier('A', 50, 130, 3));

        Checkout checkout = new Checkout(priceModifiers);

        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('A');
        checkout.reset();
        checkout.scan('Z');

        assertEquals(0, checkout.getTotal());
    }
}