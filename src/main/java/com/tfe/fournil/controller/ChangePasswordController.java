package com.tfe.fournil.controller;


import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.UserRepository;
import com.tfe.fournil.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;


/**
 * The type Change password controller.
 */
@Slf4j
@Controller
@RequestMapping(value = "/changePassword")
public class ChangePasswordController {
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
     * Show my personal data string.
     *
     * @return the string
     */
    @GetMapping("")
    public String showMyPersonalData() {

        //Nom de la JSP
        return "changePassword";
    }

    /**
     * Change password string.
     *
     * @param changePasswordDTO the change password dto
     * @param session           the session
     * @return the string
     */
    @PostMapping ("/test")
    public String changePassword( ChangePasswordDTO changePasswordDTO, HttpSession session) {
        try {
            userService.changePassword(changePasswordDTO);
            session.setAttribute("success", "Votre user a été enregistré");
        } catch (Exception e) {
            session.setAttribute("errors", Arrays.asList(e.getMessage()));
        }
        return "redirect:/changePassword";
    }



}

