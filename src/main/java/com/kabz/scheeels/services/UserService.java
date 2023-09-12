package com.kabz.scheeels.services;

import com.kabz.scheeels.entities.User;
import com.kabz.scheeels.models.OrderModel;

import java.util.Optional;

public interface UserService {
    User registerNewUser(OrderModel orderModel);

    Optional<User> findByEmail(String email);
}

