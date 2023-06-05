package com.tfe.fournil.stripe;

//envoyer le panier finale dans le checkitemDTO
//controller
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Order service 2.
 */
@Service
@Slf4j
public class OrderService2 {

    //Important pour Stripe
    private String baseURL="http://localhost:8080";

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    /**
     * The Contribution user repository.
     */
//    @Autowired
////    ContributionUserRepository contributionUserRepository;


    /**
     * Create session session.
     *
     * @param checkoutItemDtoList the checkout item dto list
     * @return the session
     * @throws StripeException the stripe exception
     */
    public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {

        log.info("Call create sessions order for list size {}", checkoutItemDtoList.size());
        // sucess and failure urls

        //page de succès ou erreur
        String successURL = baseURL + "/order/successStripe";

        String failureURL = baseURL + "/order/errorStripe";

        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();

        for (CheckoutItemDto checkoutItemDto: checkoutItemDtoList) {
            log.info(" Item dto name : " + checkoutItemDto.toString());

            sessionItemList.add(createSessionLineItem(checkoutItemDto));
        }
        log.info("CheckItemDtoList size {}", checkoutItemDtoList.size());

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureURL)
                .addAllLineItem(sessionItemList)
                .setSuccessUrl(successURL)
                .build();
        log.info("params {}", params.toString());
        return Session.create(params);
    }

    //permet de créer la session stripe
    private SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {
        log.info("Call createSessionLineItem for {}", checkoutItemDto.getProductName());
        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(checkoutItemDto))
                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity())))
                .build();
    }


    private SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
        log.info("Call createPriceData for {}", checkoutItemDto.getProductName());
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("eur")
                .setUnitAmount((long)(checkoutItemDto.getPrice()*100))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getProductName())
                                .build()
                ).build();
    }


}