package com.example.product_service.purchase.repository.entity;

import com.example.product_service.purchase.PurchaseOrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "tbl_purchase_orders")
public class PurchaseOrdersEntity {
    @Id
    @Column(name = "purchase_orders_id")
    private String purOrdersId;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "purchase_orders_date")
    private LocalDate purOrdersDate;

    @Column(name = "purchase_orders_status")
    private PurchaseOrderStatus purOrdersStatus;

    @Column(name = "purchase_orders_total_amount")
    private BigDecimal purOrderTotalAmount;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "approved_by")
    private String approvedBy;

    void BeforeSave(){
        this.purOrdersDate = LocalDate.now();
    }

}
