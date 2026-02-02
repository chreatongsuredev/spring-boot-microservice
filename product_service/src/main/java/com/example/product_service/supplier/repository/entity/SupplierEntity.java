package com.example.product_service.supplier.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tbl_suppliers")
public class SupplierEntity {

    @Id
    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_contact")
    private String supplierContact;

    @Column(name = "supplier_phone")
    private String supplierPhone;

    @Column(name = "supplier_email")
    private String supplierEmail;

    @Column(name = "supplier_address")
    private String supplierAddress;

}
