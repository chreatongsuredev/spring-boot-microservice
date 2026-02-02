package com.example.product_service.util;


import com.example.product_service.util.sequence.SequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvancedSkuGenerator {

    private final SequenceService sequenceService;

    public String generateSkuWithChecksum(String categoryPrefix, String productName) {

        // Generate sequenceZ
        Long sequence = sequenceService.getNextValue("seq_sku_product");

        // Build base SKU: CategoryPrefix + ProductCode + Sequence
        String productCode = generateProductCode(productName);
        String baseSku = String.format("%s-%s-%06d",
                categoryPrefix,
                productCode,
                sequence);

        // Add checksum
        String checksum = calculateChecksum(baseSku);

        return baseSku + "-" + checksum;
    }

    private String generateProductCode(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            return "DEF";
        }

        // Extract first 3-4 letters from product name
        String cleanName = productName.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        if (cleanName.length() <= 3) {
            return cleanName;
        }

        // Take first 3-4 characters
        return cleanName.substring(0, Math.min(4, cleanName.length()));
    }

    private String calculateChecksum(String sku) {
        int sum = 0;
        for (char c : sku.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            } else if (Character.isLetter(c)) {
                sum += (int) c;
            }
        }
        return String.format("%02d", sum % 100);
    }
}