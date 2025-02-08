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

        String input;
        while (!(input = scanner.nextLine().trim()).equalsIgnoreCase("end scan")) {
            if (!input.isEmpty()) {
                checkout.scan(input);
                printRunningTotal(checkout.getTotal());
            }
        }

        if (scanner.nextLine().trim().equalsIgnoreCase("print total")) {
            printFinalTotal(checkout.getTotal());
        }

        scanner.close();

    }

    public void printRunningTotal(int runningTotal) {
        System.out.println("Running Total: " + runningTotal);
    }

    public void printFinalTotal(int finalTotal) {
        System.out.println("Final Total: " + finalTotal);
    }
}
