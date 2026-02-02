package com.example.product_service.supplier.mapstruct;


import com.example.product_service.supplier.dto.SupplierDto;
import com.example.product_service.supplier.repository.entity.SupplierEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface SupplierMapStruct {

    @Mapping(target = "supplierId", source = "supplierId")
    @Mapping(target = "supplierName", source = "supplierName")
    SupplierDto toSupplier(SupplierEntity e);
}
