package com.kabz.scheeels.repositories;

import com.kabz.scheeels.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByActive(boolean active, Pageable pageable);

    Page<Product> findAllByActiveAndCategory(boolean active, String category, Pageable pageable);
}
