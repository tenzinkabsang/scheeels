package com.kabz.scheeels.services;

import com.kabz.scheeels.entities.Address;
import com.kabz.scheeels.entities.Order;
import com.kabz.scheeels.entities.OrderItem;
import com.kabz.scheeels.entities.User;
import com.kabz.scheeels.models.OrderModel;
import com.kabz.scheeels.models.OrderResponse;
import com.kabz.scheeels.repositories.OrderRepository;
import com.kabz.scheeels.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public OrderResponse processOrder(OrderModel orderModel, User user) {

        Address shippingAddress = new Address();
        shippingAddress.setStreet(orderModel.getStreet());
        shippingAddress.setCity(orderModel.getCity());
        shippingAddress.setState(orderModel.getState());
        shippingAddress.setZip(orderModel.getZip());

        List<OrderItem> orderItems = orderModel.getCartItems()
                .stream().map(c -> {
                    var item = new OrderItem();
                    item.setQuantity(c.getQuantity());
                    item.setProduct(c.getProduct());
                    item.setSubtotal(c.getTotal());
                    return item;
                }).toList();

        BigDecimal orderTotal = orderItems.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, (total, subtotal) -> total.add(subtotal));

        Order order = new Order();
        order.setItems(orderItems);
        order.setTotal(orderTotal);
        order.setShippingAddress(shippingAddress);
        order.setUser(user);

        orderRepo.save(order);

        var orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getId());
        orderResponse.setSuccess(true);
        orderResponse.setTrackingNumber(UUID.randomUUID().toString());

        return orderResponse;
    }
}
