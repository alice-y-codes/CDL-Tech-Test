package org.cdlkata.checkout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    private Checkout checkout;

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldStartWithZeroTotal() {
        Map<String, PriceModifier> priceModifiers = new HashMap<>();
        priceModifiers.put("A", new PriceModifier(50, 0, 0));
        PriceCalculator priceCalculator = new PriceCalculator(priceModifiers);
        Basket basket = new Basket();
        checkout = new Checkout(priceCalculator, basket);

        assertEquals(0, checkout.getTotal());
    }

    @Test
    void shouldCalculateTotalForScannedItems() {
        Map<String, PriceModifier> priceModifiers = new HashMap<>();
        priceModifiers.put("A", new PriceModifier(50, 0, 0));
        PriceCalculator priceCalculator = new PriceCalculator(priceModifiers);
        Basket basket = new Basket();
        checkout = new Checkout(priceCalculator, basket);

        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");

        assertEquals(150, checkout.getTotal());
    }

    @Test
    void shouldApplySpecialPricingForMultipleItems() {
        Map<String, PriceModifier> priceModifiers = new HashMap<>();
        priceModifiers.put("A", new PriceModifier(50, 130, 3));
        PriceCalculator priceCalculator = new PriceCalculator(priceModifiers);
        Basket basket = new Basket();
        checkout = new Checkout(priceCalculator, basket);

        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");

        assertEquals(130, checkout.getTotal());
    }

    @Test
    void shouldApplyMultiplePricingModifiers() {
        Map<String, PriceModifier> priceModifiers = new HashMap<>();
        priceModifiers.put("A", new PriceModifier(50, 130, 3));
        priceModifiers.put("B", new PriceModifier(30, 45, 2));
        priceModifiers.put("C", new PriceModifier(20, 10, 0));
        PriceCalculator priceCalculator = new PriceCalculator(priceModifiers);
        Basket basket = new Basket();
        checkout = new Checkout(priceCalculator, basket);

        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("B");
        checkout.scan("C");

        assertEquals(195, checkout.getTotal());
    }

    @Test
    void shouldHandleUnrecognisedItems() {
        Map<String, PriceModifier> priceModifiers = new HashMap<>();
        priceModifiers.put("A", new PriceModifier(50, 130, 3));
        PriceCalculator priceCalculator = new PriceCalculator(priceModifiers);
        Basket basket = new Basket();
        checkout = new Checkout(priceCalculator, basket);

        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("Z");

        assertEquals(130, checkout.getTotal());
    }
}