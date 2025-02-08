package org.cdlkata.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Checkout {
    private int total = 0;

    public void scan(char item) {
        if (item == 'A') {}
        total += 50;
    }

}
