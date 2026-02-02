package com.example.product_service.util;

import com.example.product_service.util.sequence.SequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class GenerateId {
    private final SequenceService sequenceService;

    public String generateId(String seq, String prefix) {
        Long sequenceValue = sequenceService.getNextValue(seq);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return prefix + timestamp + String.format("%04d", sequenceValue); // P202412310001
    }
    public String generateCategoryPrefix(String categoryName){
        if(categoryName.trim().length() > 3){
            return categoryName.substring(0,3).toUpperCase();
        }else{
            return categoryName.toUpperCase();
        }

    }
}
