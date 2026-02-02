package com.example.product_service.branch.repository.entity;

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
@Table(name = "tbl_branch")
public class BranchEntity {
    @Id
    @Column(name = "branch_id")
    String branchId;

    @Column(name = "branch_name")
    String branchName;

    @Column(name = "location")
    String location;

    @Column(name = "contact_phone")
    String contactPhone;

    @Column(name = "status")
    String status;

    @Column(name = "created_at")
    Timestamp createdAt;

}
