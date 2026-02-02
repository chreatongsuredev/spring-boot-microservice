package com.example.product_service.warehouse.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WarehouseRequestDto {
    String warehouseName;
    String location;
    String status;

}
