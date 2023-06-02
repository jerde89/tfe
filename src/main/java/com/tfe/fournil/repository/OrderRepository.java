package com.tfe.fournil.repository;

import com.tfe.fournil.entity.Order;
import com.tfe.fournil.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUserIdUser (long userId);
    List<Order> findByStatus(OrderStatus Status);
    List<Order> findByStatusIn(List<OrderStatus> Status);
}
