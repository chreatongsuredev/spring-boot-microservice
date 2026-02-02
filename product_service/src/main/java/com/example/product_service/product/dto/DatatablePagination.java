package com.example.product_service.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DatatablePagination<T> {
    private List<T> items;          // current page data
    private long recordsTotal;     // total records without filter
    private long recordsFiltered;  // total records after filter
    private int draw;              // optional, for DataTables

    // constructors
    public DatatablePagination() {}

    public DatatablePagination(List<T> data, long recordsTotal, long recordsFiltered, int draw) {
        this.items = data;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.draw = draw;
    }

    // getters and setters
}