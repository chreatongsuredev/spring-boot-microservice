package com.example.product_service.warehouse.service;

import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.util.Status;
import com.example.product_service.warehouse.dto.WarehouseRequestDto;
import com.example.product_service.warehouse.dto.WarehouseResponseDto;
import org.springframework.data.domain.Page;

import java.sql.Statement;

public interface WarehouseService {
    Page<WarehouseResponseDto> warehouseListPage(DataTablesInput input);
    Status createWarehouse(WarehouseRequestDto request);
}
