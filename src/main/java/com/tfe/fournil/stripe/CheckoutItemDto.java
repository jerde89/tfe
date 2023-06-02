package com.tfe.fournil.stripe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutItemDto {


    private String productName;

    private int quantity;

   private double price;

    private int productId;

    private int userId;
}
