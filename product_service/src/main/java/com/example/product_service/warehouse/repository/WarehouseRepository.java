package com.example.product_service.warehouse.repository;

import com.example.product_service.warehouse.dto.WarehouseResponseDto;
import com.example.product_service.warehouse.repository.entity.WarehouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity,String> {
    @Query(value = """
            SELECT
                 warehouse_id as warehouseId,
            	 warehouse_name as warehouseName,
            	 location,
            	 status,
            	 created_at as createdAt
            FROM tbl_warehouse
            """,nativeQuery = true)
    Page<WarehouseResponseDto> warehouseListPage(Pageable page );
}
