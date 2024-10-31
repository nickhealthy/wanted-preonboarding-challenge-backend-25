package com.wanted.clone.oneport.payments.application.service.dto;

import com.wanted.clone.oneport.payments.representation.web.request.payment.PgCorp;
import lombok.Getter;

@Getter
public class PaymentRequestDto {
    private final String pgCorpName;

    private PaymentRequestDto(String name) {
        this.pgCorpName = PgCorp.valueOf(name.toUpperCase()).toString().toLowerCase();
    }

    public static PaymentRequestDto of(String pgCorpName) {
        return new PaymentRequestDto(pgCorpName);
    }

}
