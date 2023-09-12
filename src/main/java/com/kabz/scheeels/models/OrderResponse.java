package com.kabz.scheeels.models;

import lombok.Data;

@Data
public final class OrderResponse {
    private Long orderId;
    private boolean success;
    private String trackingNumber;
}
