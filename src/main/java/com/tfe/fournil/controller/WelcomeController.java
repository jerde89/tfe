package com.tfe.fournil.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("")
public class WelcomeController {
    @GetMapping("")
    public String welcomeAppel() {
        //Nom de la JSP
        log.error("test Controller welcome");
        return "welcome";

    }

}
