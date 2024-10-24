package com.example.globalordermanagement.model.entity;

import com.example.globalordermanagement.model.enums.OrderFulfillmentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OrderFulfillment {
    private Integer orderFulfillmentId;
    private Integer orderId;
    private Integer siteProductId;
    private Integer fulfilledQuantity;
    private LocalDate expectedDeliveryDate;
    private LocalDate deliveryDate;
    private OrderFulfillmentStatus status = OrderFulfillmentStatus.Pending;

    public OrderFulfillment(Integer orderFulfillmentId,
                            Integer orderId,
                            Integer siteProductId,
                            Integer fulfilledQuantity,
                            LocalDate expectedDeliveryDate,
                            LocalDate deliveryDate,
                            OrderFulfillmentStatus status) {
        this.orderFulfillmentId = orderFulfillmentId;
        this.orderId = orderId;
        this.siteProductId = siteProductId;
        this.fulfilledQuantity = fulfilledQuantity;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }
}