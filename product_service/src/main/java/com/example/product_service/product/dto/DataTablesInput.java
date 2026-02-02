package com.example.product_service.product.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataTablesInput {
    private int start;
    private int length;
    private int Draw;
    private String search;
    private String orderColumn;
    private String orderDir;

}
