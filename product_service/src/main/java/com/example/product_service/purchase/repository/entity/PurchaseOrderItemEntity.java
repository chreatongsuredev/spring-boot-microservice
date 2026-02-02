package com.example.product_service.purchase.repository.entity;

import com.example.product_service.purchase.PurchaseOrderItemStatus;
import com.example.product_service.purchase.PurchaseOrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(name = "tbl_purchase_orders_item")
public class PurchaseOrderItemEntity {

    @Id
    @Column(name = "purchase_orders_item_id")
    private String purOrdersItemId;

    @Column(name = "purchase_orders_id")
    private String purOrdersId;

    @Column(name = "prod_id")
    private String prodId;

    @Column(name = "purchase_orders_item_quantity")
    private int purOrdersItemQuantity;

    @Column(name = "purchase_orders_item_unit_price")
    private BigDecimal purOrdersItemUnitPrice;

    @Column(name = "item_status")
    private PurchaseOrderItemStatus itemStatus;

}
