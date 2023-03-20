package com.tfe.fournil.controller;


import com.tfe.fournil.entity.MyPersonalData;
import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


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
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByUsername(username);
        log.info("user " + user.getUsername());
        log.info("test Controller MyPersonalData");
        model.addAttribute("user", user);
        //Nom de la JSP
        return "myPersonalData";
    }

    @PostMapping(value = "/modifiedUser")
    public String modifiedUser(Model model,
                               @Validated(MyPersonalData.class) User user,
//                               @Valid User user,
                               BindingResult bindingResult, HttpSession session) {
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
            User userDb = userRepository.findByUsername(user.getEmail());
            userDb.setLastname(user.getLastname());
            userDb.setFirstname(user.getFirstname());
            userDb.setPhone(user.getPhone());
            userDb.setDateOfBirth(user.getDateOfBirth());
            userDb.getAddress().setStreet(user.getAddress().getStreet());
            userDb.getAddress().setNumber(user.getAddress().getNumber());
            userDb.getAddress().setBox(user.getAddress().getBox());
            userDb.getAddress().getCity().setPostalCode(user.getAddress().getCity().getPostalCode());
            userDb.getAddress().getCity().setCityName(user.getAddress().getCity().getCityName());
            userDb.getAddress().getCity().getCountry().setCountryName(user.getAddress().getCity().getCountry().getCountryName());

            userRepository.save(userDb);



            session.setAttribute("success", "Votre user a été enregistré");

        }
        return "redirect:/myPersonalData";
    }
}




