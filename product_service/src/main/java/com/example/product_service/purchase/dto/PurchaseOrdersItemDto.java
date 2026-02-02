package com.example.product_service.purchase.dto;

import com.example.product_service.purchase.PurchaseOrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseOrdersItemDto {
    @NotBlank(message = "Product ID is required")
    String prodId;

    @Min(value = 1, message = "Quantity must be at least 1")
    int purOrdersItemQuantity;
    PurchaseOrderStatus itemStatus;

}
