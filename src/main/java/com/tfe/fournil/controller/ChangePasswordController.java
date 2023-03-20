package com.tfe.fournil.controller;


import com.tfe.fournil.repository.UserRepository;
import com.tfe.fournil.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



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

    @GetMapping("/passwordOldCorrect/{password}")
    public ResponseEntity<Boolean> checkPasswordOldCorrect(@PathVariable("password") String password) {
        return ResponseEntity.ok(userService.checkPasswordOldCorrect(password));
    }

}
