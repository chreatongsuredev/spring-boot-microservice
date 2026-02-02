package com.example.product_service.branch.service;

import com.example.product_service.branch.dto.BranchRequestDto;
import com.example.product_service.branch.dto.BranchResponseDto;
import com.example.product_service.category.dto.CategoryResponseDto;
import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.util.Status;
import org.springframework.data.domain.Page;

public interface BranchService {
    Status createBranch(BranchRequestDto request);
    Page<BranchResponseDto> branchListPage(DataTablesInput input);
}
