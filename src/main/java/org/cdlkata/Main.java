package org.cdlkata;

import org.cdlkata.checkout.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, PriceModifier> priceModifiers = new HashMap<>();
        priceModifiers.put("A", new PriceModifier(50, 130, 3));
        priceModifiers.put("B", new PriceModifier(30, 45, 2));
        priceModifiers.put("C", new PriceModifier(20, 0, 0));
        priceModifiers.put("D", new PriceModifier(15, 0, 0));

        PriceCalculator priceCalculator = new PriceCalculator(priceModifiers);
        Basket basket = new Basket();
        Checkout checkout = new Checkout(priceCalculator, basket);
        Scanner scanner = new Scanner(System.in);
        CheckoutCLI cli = new CheckoutCLI(checkout, scanner);

        cli.start();
    }
}