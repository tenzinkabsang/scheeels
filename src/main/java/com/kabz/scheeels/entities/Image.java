package com.kabz.scheeels.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "images")
@Getter
@Setter
public class Image extends BaseEntity {
    private String imageUrl;
    private boolean mainImage;
}
