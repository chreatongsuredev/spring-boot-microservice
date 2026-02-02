package com.example.product_service.supplier.controller;

import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.product.dto.DatatablePagination;
import com.example.product_service.product.dto.productDto;
import com.example.product_service.supplier.dto.SupplierDto;
import com.example.product_service.supplier.dto.SupplierRequestDto;
import com.example.product_service.supplier.dto.SupplierResponseDto;
import com.example.product_service.supplier.repository.SupplierRepository;
import com.example.product_service.supplier.service.SupplierService;
import com.example.product_service.util.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/supplier/")
public class SupplierController {
    private final SupplierService supplierService;
    @GetMapping("get-supplier")
    public List<SupplierDto> supplier() {
        return supplierService.supplierList();
    }

    @PostMapping("get-all-supplier")
    public DatatablePagination<SupplierResponseDto> findAll(@RequestBody DataTablesInput input){
        Page<SupplierResponseDto> page =  supplierService.supplierPageList(input);
        var result = new DatatablePagination<SupplierResponseDto>();
        DataTablesInput request = new DataTablesInput();
        request.setDraw(0);
        request.setStart(input.getStart());
        request.setLength(input.getLength());
        result.setItems(page.getContent());
        result.setDraw(request.getDraw());
        result.setRecordsTotal(page.getTotalElements());
        result.setRecordsFiltered(page.getTotalElements());
        return result;
    }

    @PostMapping("create-supplier")
    public Status createSupplier(@RequestBody  SupplierRequestDto request){
        return supplierService.createSupplier(request);
    }
}
