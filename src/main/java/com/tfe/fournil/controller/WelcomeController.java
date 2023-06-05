package com.tfe.fournil.controller;

import com.tfe.fournil.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Welcome controller.
 */
@Slf4j
@Controller
@RequestMapping("")
public class WelcomeController {

    /**
     * The Mail service.
     */
    @Autowired
    MailService mailService;

    /**
     * Welcome appel string.
     *
     * @return the string
     */
    @GetMapping("")
    public String welcomeAppel() {
        //Nom de la JSP
        log.error("test Controller welcome");
//        mailService.sendEmail("jeromedesch@msn.com", "test", "coucou");
        return "welcome";

    }

}
