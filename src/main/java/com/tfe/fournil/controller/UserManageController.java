package com.tfe.fournil.controller;


import com.tfe.fournil.entity.Role;
import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.RoleRepository;
import com.tfe.fournil.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Slf4j
@Controller
@RequestMapping("/userManage")
public class UserManageController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @GetMapping("")
    public String showUserManage(Model model) {
        List<Role> role = roleRepository.findAll();
        model.addAttribute("roleList", role);
        log.info("test liste de role " + role.size());
        return "userManage";
    }

    @GetMapping("/userList")
    public ResponseEntity<List<User>> ajaxShowUserList() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }



}
