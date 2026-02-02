package com.example.product_service.category.service;

import com.example.product_service.category.dto.CategoryDto;
import com.example.product_service.category.dto.CategoryResponseDto;
import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.util.Status;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> categoryList();
    Page<CategoryResponseDto> categoryListPage(DataTablesInput input);
    Status createCategory(CategoryDto request);
}
