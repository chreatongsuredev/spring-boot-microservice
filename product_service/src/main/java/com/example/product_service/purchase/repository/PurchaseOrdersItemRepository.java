package com.example.product_service.purchase.repository;

import com.example.product_service.purchase.repository.entity.PurchaseOrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrdersItemRepository extends JpaRepository<PurchaseOrderItemEntity,String> {

}
