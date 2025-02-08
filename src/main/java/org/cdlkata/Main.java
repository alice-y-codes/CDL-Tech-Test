package org.cdlkata;

import org.cdlkata.checkout.*;
import org.cdlkata.utils.PriceModifierCSVMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {

            Map<String, PriceModifier> priceModifiers = PriceModifierCSVMapper.mapCSVToPriceModifiers("src/main/resources/price-modifiers.csv");

            PriceCalculator priceCalculator = new PriceCalculator(priceModifiers);
            Basket basket = new Basket();
            Checkout checkout = new Checkout(priceCalculator, basket);
            Scanner scanner = new Scanner(System.in);
            CheckoutCLI cli = new CheckoutCLI(checkout, scanner);

            cli.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}