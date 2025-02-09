package org.cdlkata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static final String VALID_CSV_PATH = "src/test/resources/sample-price-modifiers.csv";

    @Test
    void shouldCreateCheckout() throws IOException {
        assertNotNull(Main.createCheckout(VALID_CSV_PATH));
    }

    @Test
    void shouldCreatePriceCalculator() throws IOException {
        assertNotNull(Main.createPriceCalculator(VALID_CSV_PATH));
    }

    @Test
    void shouldThrowIOExceptionForInvalidFile(@TempDir Path tempDir) {
        Path invalidFile = tempDir.resolve("nonexistent.csv");

        IOException exception = assertThrows(IOException.class, () ->
                Main.createPriceCalculator(invalidFile.toString())
        );

        assertNotNull(exception.getMessage());
    }
}
