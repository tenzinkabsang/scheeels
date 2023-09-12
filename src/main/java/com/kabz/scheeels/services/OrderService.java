package com.kabz.scheeels.services;

import com.kabz.scheeels.entities.User;
import com.kabz.scheeels.models.OrderModel;
import com.kabz.scheeels.models.OrderResponse;

public interface OrderService {
    OrderResponse processOrder(OrderModel orderModel, User user);
}

