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

    private CheckoutCLI createCheckoutCLI(Scanner mockScanner) {
        Map<String, PriceModifier> priceModifiers = new HashMap<>();
        priceModifiers.put("A", new PriceModifier(50, 130, 3, new MultiBuyDiscount()));
        priceModifiers.put("B", new PriceModifier(30, 45, 2, new MultiBuyDiscount()));
        priceModifiers.put("C", new PriceModifier(20, 0, 0, new NoDiscount()));
        priceModifiers.put("D", new PriceModifier(15, 0, 0, new NoDiscount()));
        PriceCalculator priceCalculator = new PriceCalculator(priceModifiers);

        Basket basket = new Basket();
        checkout = new Checkout(priceCalculator, basket);
        return new CheckoutCLI(checkout, mockScanner);
    }

    private String captureSystemOutput(CheckoutCLI cli) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));

        cli.start();

        System.setOut(originalSystemOut);
        return outputStream.toString();
    }

    @Test
    void shouldPrintRunningTotalAfterEachScan() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("A").thenReturn("B").thenReturn("A").thenReturn("done");

        CheckoutCLI cli = createCheckoutCLI(mockScanner);
        String output = captureSystemOutput(cli);

        assertEquals("Welcome to the checkout system!\r\n" +
                "Please scan items (e.g., A, B, C, etc.). Type 'done' to finish.\r\n" +
                "Scan item (or type 'done' to finish): Running total: 50 pence\r\n" +
                "Scan item (or type 'done' to finish): Running total: 80 pence\r\n" +
                "Scan item (or type 'done' to finish): Running total: 130 pence\r\n" +
                "Scan item (or type 'done' to finish): Checkout completed!\r\n" +
                "Final Total: 130 pence\r\n", output);
    }

    @Test
    void shouldPrintFinalTotalAfterCheckout() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("A").thenReturn("B").thenReturn("A").thenReturn("done");

        CheckoutCLI cli = createCheckoutCLI(mockScanner);
        String output = captureSystemOutput(cli);

        assertEquals("Welcome to the checkout system!\r\n" +
                "Please scan items (e.g., A, B, C, etc.). Type 'done' to finish.\r\n" +
                "Scan item (or type 'done' to finish): Running total: 50 pence\r\n" +
                "Scan item (or type 'done' to finish): Running total: 80 pence\r\n" +
                "Scan item (or type 'done' to finish): Running total: 130 pence\r\n" +
                "Scan item (or type 'done' to finish): Checkout completed!\r\n" +
                "Final Total: 130 pence\r\n", output);
    }
}
