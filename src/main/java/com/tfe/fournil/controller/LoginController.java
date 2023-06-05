package com.tfe.fournil.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Login controller.
 */
@Slf4j
@Controller
@RequestMapping(value ="/login")
public class LoginController {

    /**
     * Showlogin string.
     *
     * @return the string
     */
    @GetMapping("")
    public String showlogin()
    {
        return "login";
    }
}
