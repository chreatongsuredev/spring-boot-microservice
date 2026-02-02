package com.example.product_service.product.service;

import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.product.dto.productDto;
import com.example.product_service.product.dto.productRequestDto;
import com.example.product_service.util.Status;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;



public interface  ProductService {
    List<productDto> findProductByCategory(String categoryName);
    Page<productDto> productList(DataTablesInput input);
    Status createProduct(productRequestDto request);
}
