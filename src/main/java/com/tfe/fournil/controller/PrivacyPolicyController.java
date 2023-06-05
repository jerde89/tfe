package com.tfe.fournil.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Privacy policy controller.
 */
@Slf4j
@Controller
@RequestMapping("/privacyPolicy")
public class PrivacyPolicyController {
    /**
     * Privacy policy appel string.
     *
     * @return the string
     */
    @GetMapping("")
    public String PrivacyPolicyAppel() {
        //Nom de la JSP
        log.error("test Controller PrivacyPolicy");
        return "privacyPolicy";
    }
}