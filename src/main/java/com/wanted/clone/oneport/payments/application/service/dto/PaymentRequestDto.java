package com.wanted.clone.oneport.payments.application.service.dto;

import com.wanted.clone.oneport.payments.representation.web.request.PgCorp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
