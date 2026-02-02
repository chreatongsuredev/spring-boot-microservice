package com.example.product_service.warehouse.dto;

import java.sql.Timestamp;

public interface WarehouseResponseDto {
    String getWarehouseId();
    String getWarehouseName();
    String getLocation();
    String getStatus();
    Timestamp getCreatedAt();
}
