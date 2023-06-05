package com.tfe.fournil.controller;

import com.tfe.fournil.entity.Order;
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


/**
 * The type My oders controller.
 */
@Slf4j
@Controller
@RequestMapping("/myOrders")
public class MyOdersController {
    /**
     * The User service.
     */
    @Autowired
    UserService userService;
    /**
     * The Order repository.
     */
    @Autowired
    OrderRepository orderRepository;

    /**
     * Show my orders string.
     *
     * @param model the model
     * @return the string
     */
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
