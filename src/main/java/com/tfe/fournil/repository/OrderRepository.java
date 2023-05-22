package com.tfe.fournil.repository;

import com.tfe.fournil.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUserIdUser (long userId);
}
