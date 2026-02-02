package com.example.product_service.branch.repository;

import com.example.product_service.branch.dto.BranchResponseDto;
import com.example.product_service.branch.repository.entity.BranchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity,String> {

    @Query(value = """
            SELECT
               branch_id as branchId,
               branch_name as branchName,
               location as location,
               contact_phone as contactPhone,
               status,
               created_at as createdAt
            FROM tbl_branch
            """,nativeQuery = true)
    Page<BranchResponseDto> branchListPage(Pageable page);
}

