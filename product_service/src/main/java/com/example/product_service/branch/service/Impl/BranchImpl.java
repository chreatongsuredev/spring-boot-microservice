package com.example.product_service.branch.service.Impl;

import com.example.product_service.branch.dto.BranchRequestDto;
import com.example.product_service.branch.dto.BranchResponseDto;
import com.example.product_service.branch.repository.BranchRepository;
import com.example.product_service.branch.repository.entity.BranchEntity;
import com.example.product_service.branch.service.BranchService;
import com.example.product_service.category.dto.CategoryResponseDto;
import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.util.GenerateId;
import com.example.product_service.util.Status;
import com.example.product_service.util.pageable.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchImpl implements BranchService {
    private final GenerateId generateId;
    private final BranchRepository branchRepository;
    private final PaginationUtil paginationUtil;

    @Override
    public Status createBranch(BranchRequestDto request) {
        BranchEntity branch = new BranchEntity();
        branch.setBranchId(generateId.generateId("seq_branch_id","B"));
        branch.setBranchName(request.getBranchName());
        branch.setContactPhone(request.getContactPhone());
        branch.setLocation(request.getLocation());
        branch.setStatus(request.getStatus());
        branch.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        branchRepository.save(branch);
        Status status = new Status("Create Branch Success");
        return status;

    }

    @Override
    public Page<BranchResponseDto> branchListPage(DataTablesInput input) {
        Pageable page = paginationUtil.rePageable(input);
        Page<BranchResponseDto> branches = branchRepository.branchListPage(page);
        List<BranchResponseDto> branchList = branches.getContent();
        return new PageImpl<>(branchList,page,branches.getTotalElements());
    }

}
