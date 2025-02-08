package org.cdlkata.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Scanner;

@Data
public class CheckoutCLI {
    private final Checkout checkout;
    private final Scanner scanner;

    public CheckoutCLI(Checkout checkout, Scanner scanner) {
        this.checkout = checkout;
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("Welcome to the checkout system!");
        System.out.println("Please scan items (e.g., A, B, C, etc.). Type 'done' to finish.");

        while (true) {
            System.out.print("Scan item (or type 'done' to finish): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            checkout.scan(input);

            System.out.println("Running total: " + checkout.getTotal() + " pence");
        }

        System.out.println("Checkout completed!");
        System.out.println("Final Total: " + checkout.getTotal() + " pence");
        scanner.close();
    }
}
