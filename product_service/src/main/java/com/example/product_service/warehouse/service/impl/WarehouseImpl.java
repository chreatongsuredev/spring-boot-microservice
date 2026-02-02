package com.example.product_service.warehouse.service.impl;

import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.supplier.dto.SupplierResponseDto;
import com.example.product_service.util.GenerateId;
import com.example.product_service.util.Status;
import com.example.product_service.util.pageable.PaginationUtil;
import com.example.product_service.warehouse.dto.WarehouseRequestDto;
import com.example.product_service.warehouse.dto.WarehouseResponseDto;
import com.example.product_service.warehouse.repository.WarehouseRepository;
import com.example.product_service.warehouse.repository.entity.WarehouseEntity;
import com.example.product_service.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WarehouseImpl implements WarehouseService {
    private final PaginationUtil paginationUtil;
    private final WarehouseRepository warehouseRepository;
    private final GenerateId generateId;

    @Override
    public Page<WarehouseResponseDto> warehouseListPage(DataTablesInput input) {
        Pageable page = paginationUtil.rePageable(input);
        Page<WarehouseResponseDto> warehouse = warehouseRepository.warehouseListPage(page);
        List<WarehouseResponseDto> supplierList = warehouse.getContent();
        return new PageImpl<>(supplierList,page,warehouse.getTotalElements());
    }

    @Override
    public Status createWarehouse(WarehouseRequestDto request) {
        WarehouseEntity warehouse = new WarehouseEntity();
        warehouse.setWarehouseId(generateId.generateId("seq_warehouse_id","W"));
        warehouse.setWarehouseName(request.getWarehouseName());
        warehouse.setStatus(request.getStatus());
        warehouse.setLocation(request.getLocation());
        warehouse.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        warehouseRepository.save(warehouse);
        Status status = new Status("Create Warehouse Success");
        return status;
    }


}
