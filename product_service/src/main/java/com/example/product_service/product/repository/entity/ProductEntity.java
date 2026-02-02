package com.example.product_service.product.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "tbl_products")
@Setter
@Getter
public class ProductEntity {
    @Id
    @Column(name = "prod_id")
    private String prodId;

    @Column(name = "prod_name")
    private String prodName;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "prod_price")
    private BigDecimal prodPrice;

    @Column(name = "prod_quantity")
    private Integer prodQuantity;

    @Column(name = "prod_exp")
    private LocalDate prodExp;

    @Column(name = "description")
    private String description;

    @Column(name = "sku")
    private String sku;

    @Column(name = "prod_created_at")
    private Timestamp prodCreatedAt;
}
