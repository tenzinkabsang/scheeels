package com.kabz.scheeels.events.order;

import com.kabz.scheeels.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderProcessRequestHandler implements ApplicationListener<OrderProcessRequestEvent> {

    @Autowired
    private OrderService orderService;

    @Override
    public void onApplicationEvent(OrderProcessRequestEvent event) {

        var orderResponse =
                orderService.processOrder(event.getOrderModel(), event.getUser());

        if (orderResponse.isSuccess()) {
            // Send success email
            log.info("Order successfully placed. OrderId: {}, tracking# {}",
                    orderResponse.getOrderId(), orderResponse.getTrackingNumber());
        } else {
            // Send failure email asking them to try again.
            log.info("Your card was declined. Please try again.");
        }
    }
}
