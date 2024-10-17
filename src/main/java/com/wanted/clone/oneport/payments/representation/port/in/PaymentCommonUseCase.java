package com.wanted.clone.oneport.payments.representation.port.in;

import com.wanted.clone.oneport.payments.application.service.dto.PaymentRequestDto;

public interface PaymentCommonUseCase {
    String renderPgUi(PaymentRequestDto paymentRequestDto, String pageName) throws Exception;
}
