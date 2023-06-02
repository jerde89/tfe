package com.tfe.fournil.service;

import com.tfe.fournil.entity.Order;
import com.tfe.fournil.entity.OrderStatus;
import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;


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

    public List<Order> findByStatus() {
        List<Order> orders = orderRepository.findByStatusIn(Arrays.asList(OrderStatus.IN_PROGRESS, OrderStatus.PACKAGED, OrderStatus.DONE));
        return orders;
    }


    public void updateStatusToInProgress(List<Long> orderIds) {
        orderIds.forEach(id -> {
            Optional<Order> oderOptional = this.orderRepository.findById(id);
            if (oderOptional.isPresent()) {
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

    public void updateStatus(Long id, OrderStatus status) {
        Optional<Order> oderOptional = this.orderRepository.findById(id);
        if (oderOptional.isEmpty()) {
            return;
        }
        Order order = oderOptional.get();
        order.setStatus(status);
        orderRepository.save(order);
    }

    public List<Order> findOrderForCurrentUser() {
        Optional<User> currentUser = userService.getCurrentUser();
        if(currentUser.isEmpty()) {
            return new ArrayList<>();
        }
        User user = currentUser.get();
        List<Order> allByUserIdUser = this.orderRepository.findAllByUserIdUser(user.getIdUser());
        return allByUserIdUser;



    }
}