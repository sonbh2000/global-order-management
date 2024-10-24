package com.example.globalordermanagement.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Integer productId;
    private String productName;

    public Product(Integer productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }
}
