package com.example.product_service.purchase.service;

import com.example.product_service.purchase.dto.PurchaseRequestDto;
import com.example.product_service.purchase.dto.UpdateStatusDto;
import com.example.product_service.util.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseService {
    Status createPurchase(PurchaseRequestDto request);
    Status updateStatus(List<UpdateStatusDto> request);
}
