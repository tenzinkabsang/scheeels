package com.kabz.scheeels.events.order;

import com.kabz.scheeels.entities.User;
import com.kabz.scheeels.models.OrderModel;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class OrderProcessRequestEvent extends ApplicationEvent {
    private final OrderModel orderModel;
    private final User user;

    public OrderProcessRequestEvent(OrderModel orderModel, User user) {
        super(orderModel);
        this.orderModel = orderModel;
        this.user = user;
    }
}

