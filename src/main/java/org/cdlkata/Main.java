package org.cdlkata;

import org.cdlkata.checkout.*;
import org.cdlkata.utils.PriceModifierCSVMapper;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            String csvFilePath = args.length > 0 ? args[0] : "src/main/resources/price-modifiers.csv";

            Checkout checkout = createCheckout(csvFilePath);
            Scanner scanner = new Scanner(System.in);
            CheckoutCLI cli = new CheckoutCLI(checkout, scanner);

            cli.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Checkout createCheckout(String csvFilePath) throws IOException {
        PriceCalculator priceCalculator = createPriceCalculator(csvFilePath);
        Basket basket = new Basket();
        return new Checkout(priceCalculator, basket);
    }

    static PriceCalculator createPriceCalculator(String csvFilePath) throws IOException {
        Map<String, PriceModifier> priceModifiers = PriceModifierCSVMapper.mapCSVToPriceModifiers(csvFilePath);
        return new PriceCalculator(priceModifiers);
    }
}
