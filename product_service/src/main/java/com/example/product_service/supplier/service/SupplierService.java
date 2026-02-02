package com.example.product_service.supplier.service;

import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.supplier.dto.SupplierDto;
import com.example.product_service.supplier.dto.SupplierRequestDto;
import com.example.product_service.supplier.dto.SupplierResponseDto;
import com.example.product_service.util.Status;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SupplierService {
    List<SupplierDto> supplierList();
    Page<SupplierResponseDto> supplierPageList(DataTablesInput input);
    Status createSupplier(SupplierRequestDto request);
}
