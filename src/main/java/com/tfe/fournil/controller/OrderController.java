package com.tfe.fournil.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
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
import com.tfe.fournil.stripe.CheckoutItemDto;
import com.tfe.fournil.stripe.OrderService2;
import com.tfe.fournil.stripe.StripeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

/**
 * The type Order controller.
 */
@Slf4j
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    /**
     * The Product repository.
     */
    @Autowired
    ProductRepository productRepository;
    /**
     * The Product category repository.
     */
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    /**
     * The User service.
     */
    @Autowired
    UserService userService;
    /**
     * The Order service.
     */
    @Autowired
    OrderService orderService;

    /**
     * The Order service 2.
     */
    @Autowired
    OrderService2 orderService2;

    /**
     * The Order repository.
     */
    @Autowired
    OrderRepository orderRepository;

    /**
     * Show order string.
     *
     * @param model the model
     * @return the string
     */
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

    /**
     * Find order for current user response entity.
     *
     * @return the response entity
     */
    @GetMapping("/forCurrentUser")
    public ResponseEntity<List<OrderDTO>> findOrderForCurrentUser() {
        return ResponseEntity.ok(orderService.findOrderForCurrentUser());
    }

    /**
     * Create order response entity.
     *
     * @param order   the order
     * @param session the session
     * @return the response entity
     */
    @PostMapping("")
    public ResponseEntity<Long> createOrder(@RequestBody Order order, HttpSession session) {

        log.info("test " + order.toString());

        userService.getCurrentUser().ifPresent(order::setUser);
        order.setStatus(OrderStatus.WAITING);
        order.setCreationDate(LocalDate.now());

        orderRepository.save(order);
        return ResponseEntity.ok(order.getIdOrder());
    }


    /**
     * Show order status waiting by delivery date response entity.
     *
     * @return the response entity
     */
    @GetMapping("waitingByDeliveryDate")
    public ResponseEntity<List<OrderByDateDTO>> showOrderStatusWaitingByDeliveryDate() {
        Map<LocalDate, OrderByDateDTO> allByOrderByCreationDateDesc = this.orderService.findStatusWaitingByDeliveryDateDesc();
        return ResponseEntity.ok(new ArrayList<>(allByOrderByCreationDateDesc.values()));
    }

    /**
     * Show order status in progress by delivery date response entity.
     *
     * @return the response entity
     */
    @GetMapping("InProgressByDeliveryDate")
    public ResponseEntity<List<OrderByDateDTO>> showOrderStatusInProgressByDeliveryDate() {
        Map<LocalDate, OrderByDateDTO> allByOrderByCreationDateDesc = this.orderService.findStatusInProgressByDeliveryDateDesc();
        return ResponseEntity.ok(new ArrayList<>(allByOrderByCreationDateDesc.values()));
    }

    /**
     * Show order after status in progress response entity.
     *
     * @return the response entity
     */
    @GetMapping("afterStatusInProgress")
    public ResponseEntity<List<OrderDTO>> showOrderAfterStatusInProgress() {
        return ResponseEntity.ok(this.orderService.findByStatus());
    }

    @GetMapping("afterStatusWaiting")
    public ResponseEntity<List<OrderDTO>> showOrderAfterStatusWaiting() {
        return ResponseEntity.ok(this.orderService.findByStatusWaiting());
    }

    /**
     * Status to in progress response entity.
     *
     * @param orderIds the order ids
     * @return the response entity
     */
    @PutMapping("statusToInProgress")
    public ResponseEntity<Boolean> statusToInProgress(@RequestParam String orderIds) {
        List<Long> collect = Arrays.stream(orderIds.split(","))
                .map(s -> Long.parseLong(s.trim()))
                .collect(Collectors.toList());
        orderService.updateStatusToInProgress(collect);
        return ResponseEntity.ok(true);
    }

    /**
     * Change status response entity.
     *
     * @param orderId the order id
     * @param status  the status
     * @return the response entity
     */
    @PutMapping("changeStatus")
    public ResponseEntity<Boolean> changeStatus(@RequestParam Long orderId,
                                                @RequestParam OrderStatus status) {
        orderService.updateStatus(orderId, status);
        return ResponseEntity.ok(true);
    }

    @PutMapping("changeStatusPaid")
    public ResponseEntity<Boolean> changeStatusPaid(@RequestParam Long orderId) {
        orderService.updateStatusPaid(orderId);
        return ResponseEntity.ok(true);
    }

    /**
     * Checkout list response entity.
     *
     * @param checkoutItemDtoList the checkout item dto list
     * @return the response entity
     * @throws StripeException the stripe exception
     */
    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList)
            throws StripeException {
        log.info("Call create-checkout-session for list size {}", checkoutItemDtoList.size());
        Session session = orderService2.createSession(checkoutItemDtoList);
        log.info("Session " + session.getId());
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        log.info("StripeResponse " + stripeResponse);
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }

    /**
     * Success stripe string.
     *
     * @return the string
     */
    @GetMapping("/successStripe")
    public String successStripe() {
        return "stripeSuccess";
    }

    /**
     * Error stripe string.
     *
     * @return the string
     */
    @GetMapping("/errorStripe")
    public String errorStripe() {
        return "stripeError";
    }
}
