package com.example.product_service.category.controller;

import com.example.product_service.category.dto.CategoryDto;
import com.example.product_service.category.dto.CategoryResponseDto;
import com.example.product_service.category.service.CategoryService;
import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.product.dto.DatatablePagination;
import com.example.product_service.util.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/category/")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("get-all-categories")
    public List<CategoryDto> categoriesList(){
        return categoryService.categoryList();
    }

    @PostMapping("get-all-categories-list")
    public DatatablePagination<CategoryResponseDto> categoriesList(@RequestBody DataTablesInput input){
        Page<CategoryResponseDto> page =  categoryService.categoryListPage(input);
        var result = new DatatablePagination<CategoryResponseDto>();
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

    @PostMapping("create-category")
    public Status createCategory(@RequestBody  CategoryDto request){
        return categoryService.createCategory(request);
    }

}
