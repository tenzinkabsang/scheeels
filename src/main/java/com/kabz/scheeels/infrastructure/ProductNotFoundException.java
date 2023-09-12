package com.kabz.scheeels.infrastructure;

import lombok.extern.slf4j.Slf4j;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(Long productId) {
        super("Product not found for id = " + productId);
    }
}
