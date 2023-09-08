package com.kabz.scheeels.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String category;
    private boolean active;
}
