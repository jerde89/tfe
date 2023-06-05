package com.tfe.fournil.controller;


import com.tfe.fournil.entity.*;
import com.tfe.fournil.repository.CityRepository;
import com.tfe.fournil.repository.RoleRepository;
import com.tfe.fournil.repository.UserRepository;
import com.tfe.fournil.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


/**
 * The type User manage controller.
 */
@Slf4j
@Controller
@RequestMapping("/userManage")
public class UserManageController {

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
     * Show user manage string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("")
    public String showUserManage(Model model) {
        List<Role> role = roleRepository.findAll();
        model.addAttribute("roleList", role);
        log.info("test liste de role " + role.size());

        Optional<User> currentUser = userService.getCurrentUser();
        currentUser.ifPresent(user -> model.addAttribute("user", user));

        List<City> cityList = cityRepository.findAll();
        model.addAttribute("cityList", cityList);
        return "userManage";
    }

    /**
     * Ajax show user list response entity.
     *
     * @return the response entity
     */
    @GetMapping("/userList")
    public ResponseEntity<List<User>> ajaxShowUserList() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    /**
     * Modified user response entity.
     *
     * @param user    the user
     * @param session the session
     * @return the response entity
     */
    @PutMapping(value = "/modifiedUser")
    public ResponseEntity<User> modifiedUser(@RequestBody User user, HttpSession session) {

        log.info("save user success");
        User userDb = userRepository.findByUsername(user.getEmail());
        userDb.setLastname(user.getLastname());
        userDb.setFirstname(user.getFirstname());
        userDb.setPhone(user.getPhone());
        Address addressDb = userDb.getAddress();
        addressDb.setStreet(user.getAddress().getStreet());
        addressDb.setNumber(user.getAddress().getNumber());
        addressDb.setBox(user.getAddress().getBox());

        City city = cityRepository.findById(user.getAddress().getCity().getIdCity()).orElseThrow();
        addressDb.setCity(city);
        userDb.setRole(user.getRole());
        userDb.setEnabled(user.getEnabled());
        userRepository.save(userDb);
        return ResponseEntity.ok(userDb);
    }
}

