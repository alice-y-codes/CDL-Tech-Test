package org.cdlkata.checkout;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CheckoutCLITest {

    private Checkout checkout;

    @Test
    void shouldPrintRunningTotalAfterEachScan() {
        Map<String, PriceModifier> priceModifiers = new HashMap<>();
        priceModifiers.put("A", new PriceModifier(50, 130, 3));
        priceModifiers.put("B", new PriceModifier(30, 45, 2));
        priceModifiers.put("C", new PriceModifier(20, 0, 0));
        priceModifiers.put("D", new PriceModifier(15, 0, 0));
        PriceCalculator priceCalculator = new PriceCalculator(priceModifiers);

        Basket basket = new Basket();
        checkout = new Checkout(priceCalculator, basket);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("A").thenReturn("B").thenReturn("A").thenReturn("end scan");



        CheckoutCLI cli = new CheckoutCLI(checkout, mockScanner);
        cli.start();


        String output = outputStream.toString();

    assertEquals("Running Total: 50\r\nRunning Total: 80\r\nRunning Total: 130\r\n", output);

        System.setOut(originalSystemOut);
    }


    @Test
    void shouldPrintFinalTotalAfterCheckout() {
        Map<String, PriceModifier> priceModifiers = new HashMap<>();
        priceModifiers.put("A", new PriceModifier(50, 130, 3));
        priceModifiers.put("B", new PriceModifier(30, 45, 2));
        priceModifiers.put("C", new PriceModifier(20, 0, 0));
        priceModifiers.put("D", new PriceModifier(15, 0, 0));
        PriceCalculator priceCalculator = new PriceCalculator(priceModifiers);


        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("A").thenReturn("B").thenReturn("A").thenReturn("end scan").thenReturn("print total");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Basket basket = new Basket();

        checkout = new Checkout(priceCalculator, basket);
        CheckoutCLI cli = new CheckoutCLI(checkout, mockScanner);
        cli.start();

        String output = outputStream.toString();

        assertEquals("Running Total: 50\r\n" +
                "Running Total: 80\r\n" +
                "Running Total: 130\r\nFinal Total: 130\r\n", output);

        System.setOut(originalSystemOut);
    }
}