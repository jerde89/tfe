package com.tfe.fournil.controller;

import com.tfe.fournil.entity.Order;
import com.tfe.fournil.entity.ProductCategory;
import com.tfe.fournil.entity.OrderStatus;
import com.tfe.fournil.repository.OrderRepository;
import com.tfe.fournil.repository.ProductCategoryRepository;
import com.tfe.fournil.repository.ProductRepository;
import com.tfe.fournil.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
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
    OrderRepository orderRepository;

    @GetMapping("")
    public String showOrder(Model model) {

        List<ProductCategory> categoryList = productCategoryRepository.findAll().stream()
                .filter(ProductCategory::getEnable)
                .collect(Collectors.toList());
        for (ProductCategory productCategory: categoryList
              ) {
            int countCategory = 0;
            try {
                countCategory = productCategoryRepository.countProductByCategory(productCategory.getId());
                //si pas de produits liés à une catégorie, il indique 0
            }catch (Exception exception){
                log.info("Catégorie sans produit " + productCategory.getName());
            }
            productCategory.setCountProduct(countCategory);
        }
        model.addAttribute("categoryList", categoryList);
        return "order";
    }

    @PostMapping("")
    public ResponseEntity<Order> modifiedUser(@RequestBody Order order, HttpSession session) {
        userService.getCurrentUser().ifPresent(order::setUser);
        order.setStatus(OrderStatus.WAITING);
        orderRepository.save(order);
        return ResponseEntity.ok(order);
    }
}
