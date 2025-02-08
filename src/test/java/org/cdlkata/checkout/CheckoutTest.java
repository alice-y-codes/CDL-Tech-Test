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


}