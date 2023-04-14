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


@Slf4j
@Controller
@RequestMapping(value = "/changePassword")
public class ChangePasswordController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("")
    public String showMyPersonalData() {

        //Nom de la JSP
        return "changePassword";
    }

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
