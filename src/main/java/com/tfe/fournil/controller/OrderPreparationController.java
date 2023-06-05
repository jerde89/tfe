package com.tfe.fournil.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Order preparation controller.
 */
@Slf4j
@Controller
@RequestMapping("/orderPreparation")
public class OrderPreparationController {
    /**
     * Show order manage string.
     *
     * @return the string
     */
    @GetMapping("")
    public String showOrderManage() {
        return "orderPreparation";
    }
}
