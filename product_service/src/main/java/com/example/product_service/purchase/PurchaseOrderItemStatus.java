package com.example.product_service.purchase;

public enum PurchaseOrderItemStatus {
    PENDING,            // Not yet received
    PARTIAL_RECEIVED,   // Some quantity received
    RECEIVED,           // Full quantity received
    QUALITY_REJECTED,   // Failed quality check
    CANCELLED           // Item cancelled
}
