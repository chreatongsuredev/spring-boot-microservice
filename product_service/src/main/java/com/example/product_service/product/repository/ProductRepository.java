package com.example.product_service.product.repository;

import com.example.product_service.product.dto.productDto;
import com.example.product_service.product.repository.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,String> {

    @Query(value = """
            SELECT p.prod_name as productName,
                   c.category_name as categoryName
            
            FROM tbl_products p
            JOIN tbl_category c ON p.category_id = c.category_id
            WHERE c.category_name = :category
            """, nativeQuery = true)
    List<productDto> findProductsByCategoryName(String category);

    @Query(value = """
            SELECT
                   p.prod_exp as expireDate,
                   p.prod_id as productId,
                   pm.image_url as image,
                   p.prod_name as productName,
            	   p.sku as sku,
            	   pt.purchase_orders_item_unit_price as cost,
            	   p.prod_price as price,
            	   
            	   s.supplier_id as supplierId,
            	   s.supplier_name as supplierName,
            	   c.category_id as categoryId,
                   c.category_name as categoryName
            FROM tbl_products p
                LEFT JOIN tbl_category c ON p.category_id = c.category_id
                LEFT JOIN tbl_purchase_orders_item  pt ON pt.prod_id = p.prod_id
                LEFT JOIN tbl_product_images pm ON pm.prod_id = p.prod_id
                LEFT JOIN tbl_suppliers s ON s.supplier_id = p.supplier_id
                ORDER BY prod_created_at DESC
            """,
            countQuery = "SELECT COUNT(*) FROM tbl_products p",
            nativeQuery = true)
    Page<productDto> productList(Pageable page);
}
