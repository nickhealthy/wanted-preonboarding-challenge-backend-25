package com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.payment;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
@NoArgsConstructor
public class ResponsePaymentCommon {
    private String orderId;
    private String paymentKey;
    private String method;
    private String status;
    private int totalAmount;
    private int balanceAmount;
}
