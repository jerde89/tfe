package com.tfe.fournil.controller;


import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping(value = "/myPersonalData")
public class MyPersonalDataController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String showMyPersonalData(Model model) {
        //Avec l'objet principal, on recuprère via le security spring boot l'email, le mdp et le role
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username ;
        if (principal instanceof UserDetails) {
         username = ((UserDetails)principal).getUsername();
        } else {
                username = principal.toString();
        }
        User user = userRepository.findByUsername(username);
        log.info("user " + user.getUsername());
        log.info("test Controller MyPersonalData");

        model.addAttribute("email", user.getEmail());
        model.addAttribute("lastname", user.getLastname());

        //Nom de la JSP
        return "myPersonalData";
    }
}




