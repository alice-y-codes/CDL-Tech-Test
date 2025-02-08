package org.cdlkata.checkout;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    @Test
    void shouldCalculateTotalWithRegularPrices() {
        Map<String, PriceModifier> priceModifiers = new HashMap<>();
        priceModifiers.put("A", new PriceModifier(50, 0 , 0));
        priceModifiers.put("B", new PriceModifier(30, 0 , 0));
        PriceCalculator priceCalculator = new PriceCalculator(priceModifiers);


        Basket basket = new Basket();
        basket.addItem("A");
        basket.addItem("B");

        assertEquals(80, priceCalculator.calculateTotal(basket));
    }

    @Test
    void shouldCalculateTotalWithDiscountedPrices() {
        Map<String, PriceModifier> priceModifiers = new HashMap<>();
        priceModifiers.put("A", new PriceModifier(50, 130, 3));
        PriceCalculator priceCalculator = new PriceCalculator(priceModifiers);


        Basket basket = new Basket();

        basket.addItem("A");
        basket.addItem("A");
        basket.addItem("A");

        assertEquals(130, priceCalculator.calculateTotal(basket));
    }

}