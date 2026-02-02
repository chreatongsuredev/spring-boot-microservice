package com.example.product_service.supplier.service.impl;

import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.supplier.dto.SupplierDto;
import com.example.product_service.supplier.dto.SupplierRequestDto;
import com.example.product_service.supplier.dto.SupplierResponseDto;
import com.example.product_service.supplier.mapstruct.SupplierMapStruct;
import com.example.product_service.supplier.repository.SupplierRepository;
import com.example.product_service.supplier.repository.entity.SupplierEntity;
import com.example.product_service.supplier.service.SupplierService;
import com.example.product_service.util.GenerateId;
import com.example.product_service.util.Status;
import com.example.product_service.util.pageable.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapStruct supplierMapStruct;
    private final PaginationUtil paginationUtil;
    private final GenerateId generateId;
    Status status = new Status();

    @Override
    public List<SupplierDto> supplierList() {
        List<SupplierEntity> suppliers =  supplierRepository.findAll();
        List<SupplierDto> supplierList = Optional.ofNullable(suppliers).orElse(Collections.emptyList()).stream().map(supplierMapStruct::toSupplier).toList();
        return supplierList;
    }

    @Override
    public Page<SupplierResponseDto> supplierPageList(DataTablesInput input) {
        Pageable page = paginationUtil.rePageable(input);
        Page<SupplierResponseDto> suppliers = supplierRepository.supplierList(page);
        List<SupplierResponseDto> supplierList = suppliers.getContent();
        return new PageImpl<>(supplierList,page,suppliers.getTotalElements());
    }

    @Override
    public Status createSupplier(SupplierRequestDto request) {
        SupplierEntity supplier = new SupplierEntity();
        supplier.setSupplierId(generateId.generateId("seq_supplier_id","S"));
        supplier.setSupplierName(request.getSupplierName());
        supplier.setSupplierEmail(request.getSupplierEmail());
        supplier.setSupplierContact(request.getSupplierContact());
        supplier.setSupplierAddress(request.getSupplierAddress());
        supplier.setSupplierPhone(request.getSupplierPhone());
        supplierRepository.save(supplier);

        status.setCode(200);
        status.setMessage("Create supplier Success");

        return status;
    }


}
