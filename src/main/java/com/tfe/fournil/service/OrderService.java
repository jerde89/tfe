package com.tfe.fournil.service;

import com.tfe.fournil.entity.Order;
import com.tfe.fournil.entity.OrderDetail;
import com.tfe.fournil.entity.OrderStatus;
import com.tfe.fournil.entity.Product;
import com.tfe.fournil.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order) {
        orderRepository.save(order);
    }
//*****
    public Map<LocalDate, OrderByDateDTO> findStatusWaitingByDeliveryDateDesc() {
        List<Order> orders = orderRepository.findByStatus(OrderStatus.WAITING);
        return mapOrderByDeliveryDate(orders);
    }

    public Map<LocalDate, OrderByDateDTO> findStatusInProgressByDeliveryDateDesc() {
        List<Order> orders = orderRepository.findByStatus(OrderStatus.IN_PROGRESS);
        return mapOrderByDeliveryDate(orders);
    }

    public void updateStatusToInProgress(List<Long> orderIds) {
        orderIds.forEach(id -> {
            Optional<Order> oderOptional = this.orderRepository.findById(id);
            if(oderOptional.isPresent()){
                Order order = oderOptional.get();
                order.setStatus(OrderStatus.IN_PROGRESS);
                this.orderRepository.save(order);
            }
        });
    }

    private Map<LocalDate, OrderByDateDTO> mapOrderByDeliveryDate(List<Order> orders) {

        Map<LocalDate, OrderByDateDTO> map = new HashMap<>();
        orders.forEach(order -> {
            OrderByDateDTO orderByDate;
            if (map.containsKey(order.getDeliveryDate())) {
                orderByDate = map.get(order.getDeliveryDate());
            } else {
                orderByDate = new OrderByDateDTO();
                orderByDate.setDeliveryDate(order.getDeliveryDate());
            }
            orderByDate.addProductQuantity(order.getTotalProduct());
            orderByDate.addOrderIds(order.getIdOrder());
            orderByDate.setStatus(order.getStatus());
            order.getOrderDetails().forEach(orderByDate::addProductDetailDTO);
            map.put(order.getDeliveryDate(), orderByDate);
        });
        return map;
    }
}