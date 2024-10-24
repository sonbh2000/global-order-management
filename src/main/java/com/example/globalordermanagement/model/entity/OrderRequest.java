package com.example.globalordermanagement.model.entity;

import com.example.globalordermanagement.model.enums.OrderRequestStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OrderRequest {
    private Integer orderId;
    private LocalDate desiredDeliveryDate;
    private OrderRequestStatus status = OrderRequestStatus.Pending;

    public OrderRequest(Integer orderId, LocalDate desiredDeliveryDate, OrderRequestStatus status) {
        this.orderId = orderId;
        this.desiredDeliveryDate = desiredDeliveryDate;
        this.status = status;
    }
}
