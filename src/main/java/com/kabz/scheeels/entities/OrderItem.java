package com.kabz.scheeels.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "orderItems")
@Getter
@Setter
public class OrderItem extends BaseEntity {

    private int quantity;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private BigDecimal subtotal;
}
