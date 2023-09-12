package com.kabz.scheeels.services;

import com.kabz.scheeels.entities.Product;
import com.kabz.scheeels.infrastructure.ProductNotFoundException;
import com.kabz.scheeels.models.ProductListModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductListModel getProducts(Pageable pageable);

    List<Product> getAllActiveProducts();

    Product getById(Long id) throws ProductNotFoundException;

    ProductListModel getProductsForCategory(String category, Pageable pageable);
}
