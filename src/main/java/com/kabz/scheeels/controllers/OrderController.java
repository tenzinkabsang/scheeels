package com.kabz.scheeels.controllers;

import com.kabz.scheeels.entities.User;
import com.kabz.scheeels.events.order.OrderProcessRequestEvent;
import com.kabz.scheeels.infrastructure.RestApiController;
import com.kabz.scheeels.models.OrderModel;
import com.kabz.scheeels.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@RestApiController("order")
@Slf4j
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@Valid @RequestBody OrderModel orderModel) {

      Optional<User> existingUser = userService.findByEmail(orderModel.getEmail());

      User user = existingUser.orElse(userService.registerNewUser(orderModel));

      publisher.publishEvent(new OrderProcessRequestEvent(
              orderModel,
              user
      ));

      return ResponseEntity.ok("success");
    }
}
