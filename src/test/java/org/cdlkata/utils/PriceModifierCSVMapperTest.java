package org.cdlkata.utils;

import org.cdlkata.checkout.MultiBuyDiscount;
import org.cdlkata.checkout.PriceModifier;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PriceModifierCSVMapperTest {

    @Test
    void testMapToCSV() throws Exception {
        Map<String, PriceModifier> priceModifiers = PriceModifierCSVMapper.mapCSVToPriceModifiers("src/test/resources/sample-price-modifiers.csv");

        assertNotNull(priceModifiers);
        assertEquals(4, priceModifiers.size());
        assertTrue(priceModifiers.containsKey("A"));
        assertEquals(50, priceModifiers.get("A").getPrice());
        assertEquals(130, priceModifiers.get("A").getDiscountedPrice());
        assertEquals(3, priceModifiers.get("A").getDiscountedQuantity());
        assertInstanceOf(MultiBuyDiscount.class, priceModifiers.get("A").getDiscount());
    }

}