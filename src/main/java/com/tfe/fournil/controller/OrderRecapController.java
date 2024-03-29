package com.tfe.fournil.controller;


import com.tfe.fournil.entity.Order;
import com.tfe.fournil.entity.OrderDetail;
import com.tfe.fournil.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * The type Order recap controller.
 */
@Slf4j
@Controller
@RequestMapping("/orderRecap")
public class OrderRecapController {
    /**
     * The Order service.
     */
    @Autowired
    OrderService orderService;

    /**
     * Show order recap string.
     *
     * @return the string
     */
    @GetMapping("")
    public String showOrderRecap() {
        return "orderRecap";
    }
}