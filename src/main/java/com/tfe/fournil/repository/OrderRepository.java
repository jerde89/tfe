package com.tfe.fournil.repository;

import com.tfe.fournil.entity.Order;
import com.tfe.fournil.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Order repository.
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
    /**
     * Find all by user id user list.
     *
     * @param userId the user id
     * @return the list
     */
    List<Order> findAllByUserIdUser (long userId);

    /**
     * Find by status list.
     *
     * @param Status the status
     * @return the list
     */
    List<Order> findByStatus(OrderStatus Status);

    /**
     * Find by status in list.
     *
     * @param Status the status
     * @return the list
     */
    List<Order> findByStatusIn(List<OrderStatus> Status);
}
