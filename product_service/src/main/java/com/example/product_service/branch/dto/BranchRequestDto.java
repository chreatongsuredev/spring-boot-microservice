package com.example.product_service.branch.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchRequestDto {
    String branchName;
    String location;
    String contactPhone;
    String status;

}
