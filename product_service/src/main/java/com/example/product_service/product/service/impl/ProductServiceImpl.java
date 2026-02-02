package com.example.product_service.product.service.impl;

import com.example.product_service.category.repository.CategoryRepository;
import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.product.dto.productDto;
import com.example.product_service.product.dto.productRequestDto;
import com.example.product_service.product.repository.ProductRepository;
import com.example.product_service.product.repository.entity.ProductEntity;
import com.example.product_service.product.service.ProductService;
import com.example.product_service.product.service.support.ProductSupport;
import com.example.product_service.util.AdvancedSkuGenerator;
import com.example.product_service.util.GenerateId;
import com.example.product_service.util.Status;
import com.example.product_service.util.pageable.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductSupport productSupport;
    private final PaginationUtil paginationUtil;
    private final GenerateId generateId;
    private final CategoryRepository categoryRepository;
    private final AdvancedSkuGenerator skuGene;
    Status status = new Status();


    @Override
    public List<productDto> findProductByCategory(String categoryName) {
        return productRepository.findProductsByCategoryName(categoryName);
    }

    @Override
    public Page<productDto> productList(DataTablesInput input) {
        Pageable page = paginationUtil.rePageable(input);
        Page<productDto> products = productRepository.productList(page);
        List<productDto> productList = products.getContent();
        return new PageImpl<>(productList,page,products.getTotalElements());
    }

    @Override
    public Status createProduct(productRequestDto request) {
        String categoryPrefix = categoryRepository.categoryPrefix(request.getCategoryId());
        ProductEntity product = new ProductEntity();
        product.setProdName(request.getName());
        product.setProdPrice(request.getSellPrice());
        product.setCategoryId(request.getCategoryId());
        product.setSupplierId(request.getSupplierId());
        product.setDescription(request.getDescription());
        product.setProdId(generateId.generateId("seq_product_id","P"));
        product.setSku(skuGene.generateSkuWithChecksum(categoryPrefix,request.getName()));
        product.setProdQuantity(0);
        product.setProdExp(request.getExpireDate());
        product.setProdCreatedAt(new Timestamp( System.currentTimeMillis()));

        productRepository.save(product);
        status.setMessage("Create Product Success");
        return status;
    }

}
