package com.tfe.fournil.controller;

import com.tfe.fournil.entity.City;
import com.tfe.fournil.entity.Role;
import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.CityRepository;
import com.tfe.fournil.repository.RoleRepository;
import com.tfe.fournil.repository.UserRepository;
import com.tfe.fournil.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Register controller.
 */
@Slf4j
@Controller
@RequestMapping(value ="/register")
public class RegisterController {

    /**
     * The User repository.
     */
//injecte la classe
    @Autowired
    UserRepository userRepository;

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * The Role repository.
     */
    @Autowired
    RoleRepository roleRepository;

    /**
     * The City repository.
     */
    @Autowired
    CityRepository cityRepository;

    /**
     * Show register string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("")
    public String showRegister(Model model) {
        List<City> cityList = cityRepository.findAll();
        model.addAttribute("cityList", cityList);
        return "register";
    }

    /**
     * Add user string.
     *
     * @param model         the model
     * @param user          the user
     * @param bindingResult the binding result
     * @param session       the session
     * @return the string
     */
    @PostMapping(value = "/addUser")
    public String addUser(Model model,@Valid User user, BindingResult bindingResult, HttpSession session) {
        log.info("test id city " + user.getAddress().getCity().getIdCity() );
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
            String passwordCrypt = new BCryptPasswordEncoder().encode(user.getPassword());
            log.info("call password " + passwordCrypt);
            user.setPassword(passwordCrypt);
            user.setUsername(user.getEmail());
            Role role = roleRepository.findByRole("USER");
            user.setRole(role);
            City city = cityRepository.findById(user.getAddress().getCity().getIdCity()).orElseThrow();
            user.getAddress().setCity(city);
            userRepository.save(user);
            session.setAttribute("success", "Votre user a été enregistré");

        }
        return "redirect:/register";
    }

    /**
     * Check email unique response entity.
     *
     * @param email the email
     * @return the response entity
     */
    @GetMapping("/emailIsUnique/{email}")
    public ResponseEntity<Boolean> checkEmailUnique(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.checkIfEmailIsUnique(email));
    }
}

