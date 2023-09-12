package com.kabz.scheeels.models;

import com.kabz.scheeels.entities.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public final class CartItem {
    private Product product;
    private int quantity;

    public BigDecimal getTotal() {
        return BigDecimal.valueOf(quantity).multiply(product.getPrice());
    }
}
