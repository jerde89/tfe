package com.tfe.fournil.stripe;

import lombok.Builder;
import lombok.Data;

/**
 * The type Checkout item dto.
 */
@Data
@Builder
public class CheckoutItemDto {


    private String productName;

    private int quantity;

    private double price;

    private int productId;

    private int userId;
}

