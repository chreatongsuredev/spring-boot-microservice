package com.example.product_service.purchase.controller;

import com.example.product_service.purchase.dto.PurchaseRequestDto;
import com.example.product_service.purchase.service.PurchaseService;
import com.example.product_service.util.Status;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping("/create-purchase")
    public Status createPurchase(@RequestBody  PurchaseRequestDto request){
        return purchaseService.createPurchase(request);
    }

}
