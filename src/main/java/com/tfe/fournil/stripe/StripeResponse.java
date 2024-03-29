package com.tfe.fournil.stripe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Stripe response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StripeResponse {
    private String sessionId;
}
