package com.example.globalordermanagement.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderError {
    private Integer errorId;
    private Integer orderId;
    private String errorMessage;

    public OrderError(Integer errorId, Integer orderId, String errorMessage) {
        this.errorId = errorId;
        this.orderId = orderId;
        this.errorMessage = errorMessage;
    }
}
