package com.example.globalordermanagement.model.entity;

import com.example.globalordermanagement.model.enums.ShippingMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Site {
    private Integer siteId;
    private String siteName;
    private String location;
    private ShippingMethod shippingMethod;
    private Integer warehouseStock;

    public Site(Integer siteId, String siteName, String location, ShippingMethod shippingMethod, Integer warehouseStock) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.location = location;
        this.shippingMethod = shippingMethod;
        this.warehouseStock = warehouseStock;
    }
}
