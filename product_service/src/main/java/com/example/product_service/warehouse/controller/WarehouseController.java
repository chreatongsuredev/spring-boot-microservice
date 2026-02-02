package com.example.product_service.warehouse.controller;

import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.product.dto.DatatablePagination;
import com.example.product_service.supplier.dto.SupplierResponseDto;
import com.example.product_service.util.Status;
import com.example.product_service.warehouse.dto.WarehouseRequestDto;
import com.example.product_service.warehouse.dto.WarehouseResponseDto;
import com.example.product_service.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/warehouse/")
public class WarehouseController {
    private final WarehouseService warehouseService;

    @PostMapping("get-all-warehouse")
    public DatatablePagination<WarehouseResponseDto> warehouseListPage(@RequestBody DataTablesInput input){

        Page<WarehouseResponseDto> page =  warehouseService.warehouseListPage(input);
        var result = new DatatablePagination<WarehouseResponseDto>();
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

    @PostMapping("create-warehouse")
    public Status createWarehouse(@RequestBody  WarehouseRequestDto request){
        return warehouseService.createWarehouse(request);
    }

}
