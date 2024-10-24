package com.example.globalordermanagement.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteProduct {
    private Integer siteProductId;
    private Integer siteId;
    private Integer productId;
    private Integer availableQuantity;
    private Integer deliveryDays;

    public SiteProduct(Integer siteProductId, Integer siteId, Integer productId, Integer availableQuantity, Integer deliveryDays) {
        this.siteProductId = siteProductId;
        this.siteId = siteId;
        this.productId = productId;
        this.availableQuantity = availableQuantity;
        this.deliveryDays = deliveryDays;
    }
}
