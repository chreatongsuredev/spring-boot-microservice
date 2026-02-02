package com.example.product_service.purchase.repository;

import com.example.product_service.purchase.dto.ProductResponseDto;
import com.example.product_service.purchase.repository.entity.PurchaseOrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PurchaseOrdersRepository extends JpaRepository<PurchaseOrdersEntity,String> {
    @Query(nativeQuery = true, value = """
            select prod_id as prodId
                  ,prod_price as prodPrice 
            from tbl_products where prod_id in (:prodId)
            """)
    List<ProductResponseDto> productInfo(List<String> prodId);
}
