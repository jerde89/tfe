package com.tfe.fournil.controller;

import com.tfe.fournil.entity.Order;
import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.OrderRepository;
import com.tfe.fournil.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/myOrders")
public class MyOdersController {
    @Autowired
    UserService userService;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("")
    public String showMyOrders(Model model)
    {
        Optional<User> currentUser = userService.getCurrentUser();
        if(currentUser.isEmpty()){
            return "myOrders";
        }
        List<Order> allByUser = orderRepository.findAllByUserIdUser(currentUser.get().getIdUser());
        model.addAttribute("myOrders", allByUser);
        return "myOrders";

    }
}
