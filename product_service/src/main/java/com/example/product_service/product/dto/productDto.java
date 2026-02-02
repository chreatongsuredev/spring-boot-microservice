package com.example.product_service.product.dto;

import java.time.LocalDate;

public interface productDto {
    String getProductName();
    String getCategoryName();
    String getImage();
    String getSku();
    String getCost();
    String getPrice();
    String getQty();
    String getProductId();
    String getCategoryId();
    String getSupplierId();
    String getSupplierName();
    LocalDate getExpireDate();
}
