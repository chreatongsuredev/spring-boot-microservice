package com.example.product_service.category.mapstruct;

import com.example.product_service.category.dto.CategoryDto;
import com.example.product_service.category.repository.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapStruct {

    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "categoryName", target = "categoryName")
    CategoryDto toCateDto(CategoryEntity e);
}
