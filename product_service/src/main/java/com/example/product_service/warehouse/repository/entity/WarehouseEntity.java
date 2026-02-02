package com.example.product_service.warehouse.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tbl_warehouse")
public class WarehouseEntity {
    @Id
    @Column(name = "warehouse_id")
    String warehouseId;

    @Column(name = "warehouse_name")
    String warehouseName;

    @Column(name = "location")
    String location;

    @Column(name = "status")
    String status;

    @Column(name = "created_at")
    Timestamp createdAt;
}
