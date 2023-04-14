package com.tfe.fournil.controller;

import com.tfe.fournil.repository.UserRepository;
import com.tfe.fournil.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping(value = "/passwordForgot")
public class PasswordForgotController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("")
    public String showPasswordForgot(){
        return "passwordForgot";
    }

    @PostMapping("/checkIfEmailExist")
    public String checkEmailUnique( String email, HttpSession session) {
//        try{
//            userService.resetPassword(email);
//            return ResponseEntity.ok(true);
//        } catch (Exception e) {
//            return ResponseEntity.ok(false);
//        }
        session.removeAttribute("errors");
        session.removeAttribute("success");
        log.info("checkmail " + email);
        if (userService.checkIfEmailExist(email)){
            userService.resetPassword(email);
            session.setAttribute("success", "Un nouvel Email vous a été envoyé sur votre adresse mail");

        }else{
            session.setAttribute("errors", "Votre Email n'existe pas");

        }
//        return "passwordForgot";

        return "redirect:/passwordForgot";

    }

}
