package com.example.product_service.supplier.repository;

import com.example.product_service.supplier.dto.SupplierResponseDto;
import com.example.product_service.supplier.repository.entity.SupplierEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity,String> {
    @Query(value = """
            SELECT
                 supplier_id as supplierId,
            	 supplier_name as supplierName,
            	 supplier_contact as supplierContact,
            	 supplier_phone as supplierPhone,
            	 supplier_email as supplierEmail,
            	 supplier_address as supplierAddress
            FROM tbl_suppliers
            """,nativeQuery = true
           , countQuery = """
            SELECT COUNT(*) FROM tbl_suppliers
            """)
    Page<SupplierResponseDto> supplierList(Pageable page);
}
