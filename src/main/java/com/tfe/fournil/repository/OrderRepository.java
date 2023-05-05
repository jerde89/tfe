package com.tfe.fournil.repository;

import com.tfe.fournil.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
