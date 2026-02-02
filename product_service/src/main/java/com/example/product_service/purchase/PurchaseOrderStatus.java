package com.example.product_service.purchase;

public enum PurchaseOrderStatus {
    DRAFT,              // Being created
    PENDING_APPROVAL,   // Waiting for approval
    APPROVED,           // Approved, sent to supplier
    IN_TRANSIT,         // Goods shipped by supplier
    PARTIAL_RECEIVED,   // Some items received
    COMPLETED,          // All items received and stocked
    PENDING ,
    CANCELLED           // Order cancelled
}
