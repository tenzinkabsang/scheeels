package com.kabz.scheeels.controllers;

import com.kabz.scheeels.entities.Product;
import com.kabz.scheeels.infrastructure.ProductNotFoundException;
import com.kabz.scheeels.models.ProductListModel;
import com.kabz.scheeels.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;


@RestController
@Slf4j
public class ProductController {

    @Value("${products.pageSize}")
    private int pageSize;

    @Autowired
    private ProductService productService;
    @GetMapping
    @RequestMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        var result = productService.getAllActiveProducts();
        return ok(result);
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getById(id);
    }

    @GetMapping("/products/page/{pageNumber}")
    public ProductListModel getPaginatedProducts(@PathVariable("pageNumber") int pageNumber) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        ProductListModel result = productService.getProducts(pageable);

        return result;
    }

    @GetMapping("/{category}/page/{pageNumber}")
    public ProductListModel getProductsForCategory(@PathVariable("category") String category,
            @PathVariable("pageNumber") int pageNumber) {

        log.info("Category: {}, PageNumber: {}", category, pageNumber);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        ProductListModel result = productService.getProductsForCategory(category, pageable);
        return result;
    }




}
