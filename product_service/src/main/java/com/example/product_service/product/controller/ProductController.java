package com.example.product_service.product.controller;

import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.product.dto.DatatablePagination;
import com.example.product_service.product.dto.productDto;
import com.example.product_service.product.dto.productRequestDto;
import com.example.product_service.product.service.ProductService;
import com.example.product_service.util.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product/")
public class ProductController {

    private final ProductService productService;

    @GetMapping("find-product-by-category")
    public List<productDto> findProductByCategory(@RequestParam String categoryName){
        return productService.findProductByCategory(categoryName);
    }
    @PostMapping("get-all-product")
    public DatatablePagination<productDto> findAll(@RequestBody DataTablesInput input){
        Page<productDto> page =  productService.productList(input);
        var result = new DatatablePagination<productDto>();
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
    @PostMapping("create-product")
    public Status createProduct(@RequestBody  productRequestDto request){
        return productService.createProduct(request);
    }
}
