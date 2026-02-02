package com.example.product_service.purchase.service.impl;

import com.example.product_service.purchase.PurchaseOrderItemStatus;
import com.example.product_service.purchase.PurchaseOrderStatus;
import com.example.product_service.purchase.dto.ProductResponseDto;
import com.example.product_service.purchase.dto.PurchaseOrdersItemDto;
import com.example.product_service.purchase.dto.PurchaseRequestDto;
import com.example.product_service.purchase.dto.UpdateStatusDto;
import com.example.product_service.purchase.repository.PurchaseOrdersItemRepository;
import com.example.product_service.purchase.repository.PurchaseOrdersRepository;
import com.example.product_service.purchase.repository.entity.PurchaseOrderItemEntity;
import com.example.product_service.purchase.repository.entity.PurchaseOrdersEntity;
import com.example.product_service.purchase.service.PurchaseService;
import com.example.product_service.util.GenerateId;
import com.example.product_service.util.Status;
import com.example.product_service.util.exception.DatabaseException;
import com.example.product_service.util.exception.ValidationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PurchaseServiceImp implements PurchaseService {

    private final GenerateId generateId;
    private final PurchaseOrdersRepository purchaseOrdersRepository;
    private final PurchaseOrdersItemRepository purchaseOrdersItemRepository;
    Status status = new Status();

    @Transactional
    @Override
    public Status createPurchase(PurchaseRequestDto request) {

        // ---------- Validation ----------
        if(request.getSupplierId() == null) {
            throw new ValidationException("Supplier ID is required");
        }
        if(request.getPurchaseItem() == null || request.getPurchaseItem().isEmpty()) {
            throw new ValidationException("Purchase items cannot be empty");
        }

        // ---------- Prepare IDs ----------
        String purchaseOrderId = generateId.generateId("purchase_order_id_seq","PUR");
        Map<String, PurchaseOrdersItemDto> productsMap = request.getPurchaseItem().stream()
                .collect(Collectors.toMap(PurchaseOrdersItemDto::getProdId, Function.identity()));
        List<String> prodIds = new ArrayList<>(productsMap.keySet());

        // ---------- Fetch products ----------
        List<ProductResponseDto> productResponse = purchaseOrdersRepository.productInfo(prodIds);
        if (productResponse.size() != prodIds.size()) {
            throw new ValidationException("Some products are invalid or missing");
        }

        // ---------- Prepare items ----------
        List<PurchaseOrderItemEntity> purchaseOrderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (ProductResponseDto product : productResponse) {
            PurchaseOrdersItemDto dto = productsMap.get(product.getProdId());
            int quantity = dto.getPurOrdersItemQuantity();
            if (quantity <= 0) {
                throw new ValidationException("Invalid quantity for product " + product.getProdId());
            }

            BigDecimal itemTotal = product.getProdPrice().multiply(BigDecimal.valueOf(quantity));
            totalAmount = totalAmount.add(itemTotal);

            PurchaseOrderItemEntity itemEntity = new PurchaseOrderItemEntity();
            itemEntity.setPurOrdersItemId(generateId.generateId("purchase_order_item_id_seq","PURI"));
            itemEntity.setPurOrdersId(purchaseOrderId);
            itemEntity.setProdId(product.getProdId());
            itemEntity.setPurOrdersItemQuantity(quantity);
            itemEntity.setPurOrdersItemUnitPrice(product.getProdPrice());
            itemEntity.setItemStatus(PurchaseOrderItemStatus.PENDING);

            purchaseOrderItems.add(itemEntity);
        }

        // ---------- Prepare order ----------
        PurchaseOrdersEntity orderEntity = new PurchaseOrdersEntity();
        orderEntity.setPurOrdersId(purchaseOrderId);
        orderEntity.setSupplierId(request.getSupplierId());
        orderEntity.setPurOrdersStatus(PurchaseOrderStatus.IN_TRANSIT);
        orderEntity.setPurOrderTotalAmount(totalAmount);
        orderEntity.setCreatedBy(request.getCreatedBy());

        // ---------- Save to DB ----------
        try {
            purchaseOrdersRepository.save(orderEntity);
            purchaseOrdersItemRepository.saveAll(purchaseOrderItems);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to save purchase order", ex);
        }

        // ---------- Return status ----------
        status.setCode(200);
        status.setMessage("Purchase Success");
        return status;
    }

    @Override
    public Status updateStatus(List<UpdateStatusDto> request) {
        return null;
    }


}
