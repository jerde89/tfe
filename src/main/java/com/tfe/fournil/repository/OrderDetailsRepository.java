package com.tfe.fournil.repository;

import com.tfe.fournil.entity.Order;
import com.tfe.fournil.entity.OrderDetail;
import com.tfe.fournil.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Order repository.
 */
public interface OrderDetailsRepository extends JpaRepository<OrderDetail,Long> {
}
