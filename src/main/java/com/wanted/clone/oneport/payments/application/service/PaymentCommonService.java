package com.wanted.clone.oneport.payments.application.service;

import com.wanted.clone.oneport.payments.application.port.out.pg.PgWidget;
import com.wanted.clone.oneport.payments.application.service.dto.PaymentRequestDto;
import com.wanted.clone.oneport.payments.representation.port.in.PaymentCommonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentCommonService implements PaymentCommonUseCase {
    @Override
    public String renderPgUi(PaymentRequestDto paymentRequestDto, String pageType) throws Exception {
        String pgCorpName = Optional.ofNullable(paymentRequestDto.getPgCorpName())
                .orElseThrow(() -> new IllegalArgumentException("PG Corp Name cannot be null"));

        PgWidget pgWidget = PgWidget.of(pgCorpName);
        String page = "";
        switch (pageType) {
            case "checkout" -> page = pgWidget.checkout();
            case "success" -> page = pgWidget.success();
            case "fail" -> page = pgWidget.fail();
            default -> throw new IllegalArgumentException("Invalid pageType name: " + pageType);
        }

        return page;
    }
}
