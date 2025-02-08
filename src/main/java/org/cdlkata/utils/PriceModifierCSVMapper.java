package org.cdlkata.utils;

import com.opencsv.CSVReader;
import org.cdlkata.checkout.PriceModifier;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceModifierCSVMapper {

    public static Map<String, PriceModifier> mapCSVToPriceModifiers(String filePath) throws IOException {
        Map<String, PriceModifier> priceModifiers = new HashMap<>();

        try (Reader reader = new FileReader(filePath);
             CSVReader csvReader = new CSVReader(reader)) {
            List<String[]> records = csvReader.readAll();

            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);
                String itemName = record[0].trim();
                int unitPrice = Integer.parseInt(record[1].trim());
                int specialPrice = Integer.parseInt(record[2].trim());
                int specialQuantity = Integer.parseInt(record[3].trim());

                PriceModifier price = new PriceModifier(unitPrice, specialPrice, specialQuantity);
                priceModifiers.put(itemName, price);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error reading or parsing CSV file", e);
        }

        return priceModifiers;
    }
}
