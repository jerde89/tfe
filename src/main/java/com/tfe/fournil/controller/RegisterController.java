package com.tfe.fournil.controller;

import com.tfe.fournil.entity.Feedback;
import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.FeedbackRepository;
import com.tfe.fournil.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(value ="/register")
public class RegisterController {

    //injecte la classe
    @Autowired
    UserRepository userRepositoryRepository;


    @GetMapping("")
    public String showRegister() {
        return "register";
    }

    @PostMapping(value = "")
    public String addUser(Model model,@Valid User user, BindingResult bindingResult, HttpSession session) {
        log.info("call send user " + user);
        session.removeAttribute("errors");
        session.removeAttribute("success");
        if (bindingResult.hasErrors()) {
            log.error("call error");
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            log.error(errors.toString());
            // model.addAttribute("errors", errors);
            session.setAttribute("errors", errors);
            session.setAttribute("user", user);
        } else {
            log.info("save user success");
            userRepositoryRepository.save(user);
            session.setAttribute("success", "Votre user a été enregistré");

        }
        return "redirect:/register";
    }
}