package com.kabz.scheeels.models;

import lombok.Data;

import java.util.List;

@Data
public final class OrderModel {
    // User info
    private String firstName;
    private String lastName;
    private String email;

    // Shipping info
    private String street;
    private String city;
    private String state;
    private String zip;

    private List<CartItem> cartItems;
}
