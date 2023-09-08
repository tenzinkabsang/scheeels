package com.kabz.scheeels.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address extends BaseEntity {
    private String street;
    private String city;
    private String state;
    private String zip;
}


