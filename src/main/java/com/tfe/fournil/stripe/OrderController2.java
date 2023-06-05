package com.tfe.fournil.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * The type Order controller 2.
 */
@Slf4j
@Controller
public class OrderController2 {
    @Autowired
    private OrderService2 orderService2;

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
        log.info("StripeResponse " + stripeResponse.toString());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }
}