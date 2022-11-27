package com.tfe.fournil.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value ="/register")
public class RegisterController {

    @GetMapping("")
    public String showRegister()
    {
        return "register";
    }
}