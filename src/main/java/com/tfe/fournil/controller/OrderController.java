package com.tfe.fournil.controller;

import com.tfe.fournil.entity.Order;
import com.tfe.fournil.entity.OrderStatus;
import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.repository.OrderRepository;
import com.tfe.fournil.repository.ProductCategoryRepository;
import com.tfe.fournil.repository.ProductRepository;
import com.tfe.fournil.service.OrderByDateDTO;
import com.tfe.fournil.service.OrderDTO;
import com.tfe.fournil.service.OrderService;
import com.tfe.fournil.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("")
    public String showOrder(Model model) {
        List<ProductCategory> categoryList = productCategoryRepository.findAll().stream()
                .filter(ProductCategory::getEnable)
                .collect(Collectors.toList());
        for (ProductCategory productCategory : categoryList
        ) {
            int countCategory = 0;
            try {
                countCategory = productCategoryRepository.countProductByCategory(productCategory.getId());
                //si pas de produits liés à une catégorie, il indique 0
            } catch (Exception exception) {
                log.info("Catégorie sans produit " + productCategory.getName());
            }
            productCategory.setCountProduct(countCategory);
        }
        model.addAttribute("categoryList", categoryList);
        return "order";
    }

    @GetMapping("/forCurrentUser")
    public ResponseEntity<List<OrderDTO>> findOrderForCurrentUser() {
        return ResponseEntity.ok(orderService.findOrderForCurrentUser());
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createOrder(@RequestBody Order order, HttpSession session) {
        userService.getCurrentUser().ifPresent(order::setUser);
        order.setStatus(OrderStatus.WAITING);
        order.setCreationDate(LocalDate.now());
        orderRepository.save(order);
        return ResponseEntity.ok(true);
    }


    @GetMapping("waitingByDeliveryDate")
    public ResponseEntity<List<OrderByDateDTO>> showOrderStatusWaitingByDeliveryDate() {
        Map<LocalDate, OrderByDateDTO> allByOrderByCreationDateDesc = this.orderService.findStatusWaitingByDeliveryDateDesc();
        return ResponseEntity.ok(new ArrayList<>(allByOrderByCreationDateDesc.values()));
    }

    @GetMapping("InProgressByDeliveryDate")
    public ResponseEntity<List<OrderByDateDTO>> showOrderStatusInProgressByDeliveryDate() {
        Map<LocalDate, OrderByDateDTO> allByOrderByCreationDateDesc = this.orderService.findStatusInProgressByDeliveryDateDesc();
        return ResponseEntity.ok(new ArrayList<>(allByOrderByCreationDateDesc.values()));
    }

    @GetMapping("afterStatusInProgress")
    public ResponseEntity<List<OrderDTO>> showOrderAfterStatusInProgress() {
        return ResponseEntity.ok(this.orderService.findByStatus());
    }

    @PutMapping("statusToInProgress")
    public ResponseEntity<Boolean> statusToInProgress(@RequestParam String orderIds){
        List<Long> collect = Arrays.stream(orderIds.split(","))
                .map(s -> Long.parseLong(s.trim()))
                .collect(Collectors.toList());
        orderService.updateStatusToInProgress(collect);
        return ResponseEntity.ok(true);
    }

    @PutMapping("changeStatus")
    public ResponseEntity<Boolean> changeStatus(@RequestParam Long orderId,
                                                @RequestParam OrderStatus status){
        orderService.updateStatus(orderId, status);
        return ResponseEntity.ok(true);
    }
}
