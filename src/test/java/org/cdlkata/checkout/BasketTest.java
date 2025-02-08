package org.cdlkata.checkout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    @Test
    void shouldAddItemToBasket() {
        Basket basket = new Basket();

        basket.addItem("A");
        basket.addItem("B");

        assertEquals(1, basket.getItemQuantity("A"));
        assertEquals(1, basket.getItemQuantity("B"));
    }

    @Test
    void shouldTrackMultipleBasketItems() {
        Basket basket = new Basket();

        basket.addItem("A");
        basket.addItem("A");
        basket.addItem("A");

        assertEquals(3, basket.getItemQuantity("A"));

    }

}