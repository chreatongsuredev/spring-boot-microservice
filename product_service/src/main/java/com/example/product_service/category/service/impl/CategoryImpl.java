package com.example.product_service.category.service.impl;

import com.example.product_service.category.dto.CategoryDto;
import com.example.product_service.category.dto.CategoryResponseDto;
import com.example.product_service.category.mapstruct.CategoryMapStruct;
import com.example.product_service.category.repository.CategoryRepository;
import com.example.product_service.category.repository.entity.CategoryEntity;
import com.example.product_service.category.service.CategoryService;
import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.product.service.support.ProductSupport;
import com.example.product_service.util.ApiResponse;
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

@RequiredArgsConstructor
@Service
public class CategoryImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapStruct categoryMapStruct;
    private final ProductSupport productSupport;
    private final GenerateId generateId;
    private final PaginationUtil paginationUtil;

    @Override
    public List<CategoryDto> categoryList() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        List<CategoryDto> categoriesDto = Optional.ofNullable(categories)
                  .orElse(Collections.emptyList())
                  .stream().map(categoryMapStruct::toCateDto).toList();

        return categoriesDto;


}
    @Override
    public Page<CategoryResponseDto> categoryListPage(DataTablesInput input) {
        Pageable page = paginationUtil.rePageable(input);
        Page<CategoryResponseDto> categories = categoryRepository.categoryList(page);
        List<CategoryResponseDto> categoryList = categories.getContent();
        return new PageImpl<>(categoryList,page,categories.getTotalElements());

    }

    @Override
    public Status createCategory(CategoryDto request) {
        CategoryEntity category = new CategoryEntity();
        category.setCategoryName(request.getCategoryName());
        category.setCategoryId(generateId.generateId("seq_category_id","C"));
        category.setCategoryPrefix(generateId.generateCategoryPrefix(request.getCategoryName()));
        categoryRepository.save(category);
        Status status = new Status("Create Category Success");

        return status;
    }
}
