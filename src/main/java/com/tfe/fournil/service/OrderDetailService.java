package com.tfe.fournil.service;

import com.tfe.fournil.entity.*;
import com.tfe.fournil.repository.OrderDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Order service.
 */
@Service
@Slf4j
public class OrderDetailService {
    /**
     * The Order repository.
     */
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    public void delete(OrderDetail orderDetail){
        this.orderDetailsRepository.deleteById(orderDetail.getIdOrderDetail());
//        this.orderDetailsRepository.delete(orderDetail);
    }



}
