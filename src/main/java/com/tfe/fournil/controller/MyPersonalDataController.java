package com.tfe.fournil.controller;


import com.tfe.fournil.entity.City;
import com.tfe.fournil.entity.MyPersonalData;
import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.CityRepository;
import com.tfe.fournil.repository.UserRepository;
import com.tfe.fournil.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Controller
@RequestMapping(value = "/myPersonalData")
public class MyPersonalDataController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    CityRepository cityRepository;

    @GetMapping("")
    public String showMyPersonalData(Model model) {
        log.info("test Controller MyPersonalData");
        Optional<User> currentUser = userService.getCurrentUser();
        currentUser.ifPresent(user -> model.addAttribute("user", user));
        //Nom de la JSP
        List<City> cityList = cityRepository.findAll();
        model.addAttribute("cityList", cityList);
        return "myPersonalData";
    }

    @PostMapping(value = "/modifiedUser")
    public String modifiedUser(Model model,
                               @Validated(MyPersonalData.class) User user,
                               BindingResult bindingResult, HttpSession session) {
        log.info("call send user " + user);
        session.removeAttribute("errors");
        session.removeAttribute("success");
        if (bindingResult.hasErrors()) {
            log.error("call error");
            List<String> errors = bindingResult.getAllErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
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
//            userDb.getAddress().getCity().setPostalCode(user.getAddress().getCity().getPostalCode());
//            userDb.getAddress().getCity().setCityName(user.getAddress().getCity().getCityName());


            City city = cityRepository.findById(user.getAddress().getCity().getIdCity()).orElseThrow();
            userDb.getAddress().setCity(city);
            userRepository.save(userDb);


            session.setAttribute("success", "Votre user a été enregistré");

        }
        return "redirect:/myPersonalData";
    }
}




