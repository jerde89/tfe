package com.tfe.fournil.service;

import com.tfe.fournil.entity.Order;
import com.tfe.fournil.repository.OrderRepository;
import com.tfe.fournil.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.save(order);
    }
}
