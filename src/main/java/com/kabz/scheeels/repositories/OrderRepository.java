package com.kabz.scheeels.repositories;

import com.kabz.scheeels.entities.Order;
import com.kabz.scheeels.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}


