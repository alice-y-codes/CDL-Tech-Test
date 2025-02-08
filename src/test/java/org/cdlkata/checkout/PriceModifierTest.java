package org.cdlkata.checkout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceModifierTest {

    @Test
    void shouldCreatePriceModifier() {
        PriceModifier priceModifier = new PriceModifier(50, 130, 3);

        assertEquals(50, priceModifier.getPrice());
        assertEquals(130, priceModifier.getDiscountedPrice());
        assertEquals(3, priceModifier.getDiscountedQuantity());
    }

    @Test
    void shouldApplyDiscountedPrice() {
        PriceModifier priceModifier = new PriceModifier(50, 130, 3);

        assertEquals(130, priceModifier.getPriceForQuantity(3));
        assertEquals(100, priceModifier.getPriceForQuantity(2));
    }

    @Test
    void shouldValidateDiscountedOffer() {
        PriceModifier priceModifier = new PriceModifier( 50, 130, 3);

        assertTrue(priceModifier.isDiscountApplicable());
    }
}