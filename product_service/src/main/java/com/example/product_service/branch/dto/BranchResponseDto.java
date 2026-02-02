package com.example.product_service.branch.dto;

import java.sql.Timestamp;

public interface BranchResponseDto {
    String getBranchId();
    String getBranchName();
    String getLocation();
    String getContactPhone();
    String getStatus();
    Timestamp getCreatedAt();

}
