package com.example.product_service.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class productRequestDto {
    String name;
    BigDecimal sellPrice;
    String categoryId;
    String supplierId;
    String description;
    String image;
    LocalDate expireDate;
}
