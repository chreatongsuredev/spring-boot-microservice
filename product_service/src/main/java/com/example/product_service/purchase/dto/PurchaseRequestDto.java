package com.example.product_service.purchase.dto;

import com.example.product_service.purchase.PurchaseOrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseRequestDto {
    @NotBlank(message = "Supplier ID is required")
    String SupplierId;
    PurchaseOrderStatus purchaseOrdesStatus;
    String createdBy;
    String approvedBy;

    @Size(min = 1, message = "At least one purchase item is required")
    List<PurchaseOrdersItemDto> purchaseItem;
}
