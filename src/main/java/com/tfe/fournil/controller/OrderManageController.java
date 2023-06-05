package com.tfe.fournil.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Order manage controller.
 */
@Slf4j
@Controller
@RequestMapping("/orderManage")
public class OrderManageController {
    /**
     * Show order manage string.
     *
     * @return the string
     */
    @GetMapping("")
    public String showOrderManage() {
        return "orderManage";
    }
}
