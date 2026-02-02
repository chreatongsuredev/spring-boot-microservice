package com.example.product_service.supplier.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.logging.Level;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierRequestDto {
    String supplierName;
    String supplierContact;
    String supplierPhone;
    String supplierEmail;
    String supplierAddress;
}
