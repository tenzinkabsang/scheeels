package com.kabz.scheeels.services;

import com.kabz.scheeels.entities.Product;
import com.kabz.scheeels.infrastructure.ProductNotFoundException;
import com.kabz.scheeels.models.ProductListModel;
import com.kabz.scheeels.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public ProductListModel getProducts(Pageable pageable) {
        Page<Product> pagedProducts = productRepo.findAllByActive(true, pageable);
        return new ProductListModel(null, pagedProducts);
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepo.findById(id);

        return product.orElseThrow(
                () -> new ProductNotFoundException(id));
    }

    @Override
    public ProductListModel getProductsForCategory(String category, Pageable pageable) {
        var pageProducts = productRepo.findAllByActiveAndCategory(
                true, category, pageable);

        return new ProductListModel(category, pageProducts);
    }
}





















