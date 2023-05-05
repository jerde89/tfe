package com.tfe.fournil.controller;


import com.tfe.fournil.entity.Order;
import com.tfe.fournil.entity.OrderDetail;
import com.tfe.fournil.entity.Product;
import com.tfe.fournil.service.FileStorageService;
import com.tfe.fournil.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@Slf4j
@Controller
@RequestMapping("/orderRecap")
public class OrderRecapController {
    @Autowired
    OrderService orderService;

    @GetMapping("")
    public String showOrderRecap() {
        return "orderRecap";
    }

    @PostMapping(value = "")
    //@RequestBody Product product => va recevoir un objet JSON de type Product appellé product
    //Objet Product va recevoir les champ nom, desription, price, taxRate, category, ... de la js ProductJs d'une requête ajax)
    public ResponseEntity<Product> addProduct(@RequestBody NewOrderDTO newOrder){
        Order order = new Order();
        order.setOrder_date(newOrder.getDateOfReceipt());
        order.setDeliveryMode(newOrder.getDeliveryMode());

        newOrder.getOrderDetails().forEach(newOrderDetailDto -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setPrice(newOrderDetailDto.getPriceUnit());
            orderDetail.setQuantity(newOrderDetailDto.getQuantity());
            order.set
        });
        orderService.addOrder(order);
    }
}