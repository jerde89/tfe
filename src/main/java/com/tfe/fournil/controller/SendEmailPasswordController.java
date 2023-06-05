package com.tfe.fournil.controller;

import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.UserRepository;
import com.tfe.fournil.service.GeneratePassword;
import com.tfe.fournil.service.MailService;
import com.tfe.fournil.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Send email password controller.
 */
@Controller
@RequestMapping(value = "/sendEmailPassword")
@Slf4j
public class SendEmailPasswordController
{
    /**
     * The User repository.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * Send email password string.
     *
     * @param email the email
     * @return the string
     */
    @PostMapping (value ="")
    public String sendEmailPassword (String email){
        User user = userRepository.findByUsername(email);
        if (user != null){
            userService.sendMailResetPassword(user, "Reinitialisation du mot de passe");
        }
        return "myPersonalData";
    }
}

