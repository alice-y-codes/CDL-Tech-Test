package org.cdlkata.checkout;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceModifierTest {

    @Test
    void shouldCreatePriceModifierWithCorrectValues() {
        Discount mockDiscount = mock(Discount.class);
        PriceModifier priceModifier = new PriceModifier(100, 80, 3, mockDiscount);

        assertEquals(100, priceModifier.getPrice());
        assertEquals(80, priceModifier.getDiscountedPrice());
        assertEquals(3, priceModifier.getDiscountedQuantity());
        assertEquals(mockDiscount, priceModifier.getDiscount());
    }

    @Test
    void shouldHandleNoDiscount() {
        PriceModifier priceModifier = new PriceModifier(100, 80, 3, null);

        assertNotNull(priceModifier);
        assertNull(priceModifier.getDiscount());
    }
}
