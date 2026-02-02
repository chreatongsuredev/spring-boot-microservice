package com.example.product_service.branch.controller;

import com.example.product_service.branch.dto.BranchRequestDto;
import com.example.product_service.branch.dto.BranchResponseDto;
import com.example.product_service.branch.service.BranchService;
import com.example.product_service.product.dto.DataTablesInput;
import com.example.product_service.product.dto.DatatablePagination;
import com.example.product_service.util.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/branch/")
public class BranchController {
    private final BranchService branchService;

    @PostMapping("create-branch")
    public Status createBranch(@RequestBody BranchRequestDto request){
        return branchService.createBranch(request);
    }

    @PostMapping("get-all-branch-list")
    public DatatablePagination<BranchResponseDto> branchesList(@RequestBody DataTablesInput input){
        Page<BranchResponseDto> page =  branchService.branchListPage(input);
        var result = new DatatablePagination<BranchResponseDto>();
        DataTablesInput request = new DataTablesInput();
        request.setDraw(0);
        request.setStart(input.getStart());
        request.setLength(input.getLength());
        result.setItems(page.getContent());
        result.setDraw(request.getDraw());
        result.setRecordsTotal(page.getTotalElements());
        result.setRecordsFiltered(page.getTotalElements());
        return result;
    }


}
