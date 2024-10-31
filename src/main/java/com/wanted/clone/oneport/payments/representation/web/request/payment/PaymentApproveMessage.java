package com.wanted.clone.oneport.payments.representation.web.request.payment;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentApproveMessage {
    private String siteCode;
    private int pgCorp;
    private String paymentKey;
    private String orderId;
    private int totalAmount;

    @Override
    public String toString(){
        return "PaymentApproveMessage [site_code=" + siteCode +
                ", pg_corp=" + pgCorp +
                ", payment_key=" + paymentKey +
                ", order_id=" + orderId +
                ", total_amount=" + totalAmount +
                "]";
    }
}
