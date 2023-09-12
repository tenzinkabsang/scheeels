package com.kabz.scheeels.models;

import com.kabz.scheeels.entities.Product;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

public record ProductListModel(String currentCategory, Page<Product> products){}
