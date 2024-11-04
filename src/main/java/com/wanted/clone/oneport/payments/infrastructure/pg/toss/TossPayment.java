package com.wanted.clone.oneport.payments.infrastructure.pg.toss;

import com.wanted.clone.oneport.payments.application.port.out.pg.PaymentAPIs;
import com.wanted.clone.oneport.payments.infrastructure.pg.CommonApproveMessage;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.request.TossApproveMessage;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.ResponsePaymentApproved;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.ResponsePaymentCancel;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.ResponsePaymentSettlements;
import com.wanted.clone.oneport.payments.presentation.web.request.payment.ReqPaymentApprove;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TossPayment implements PaymentAPIs {
    private final TossPaymentAPIs tossClient;

    @Override
    public ResponsePaymentApproved requestPaymentApprove(ReqPaymentApprove requestMessage) throws IOException {
        Response<ResponsePaymentApproved> response = tossClient.paymentFullfill(TossApproveMessage.from(requestMessage)).execute();
        if (response.isSuccessful()) {
            return response.body();
        }

        throw new IOException(response.message());
    }

    @Override
    public boolean isPaymentApproved(String status) {
        return "DONE".equalsIgnoreCase(status);
    }

    @Override
    public ResponsePaymentCancel requestPaymentCancel(String paymentKey) throws IOException {
        Response<ResponsePaymentCancel> response = tossClient.paymentCancel(paymentKey, cancelMessage).execute();
        if (response.isSuccessful()) {
            return response.body();
        }

        throw new IOException(response.message());
    }

    @Override
    public List<ResponsePaymentSettlements> requestPaymentSettlement() throws IOException {
        String startDate = paymentSettlement.getStartDate();
        String endDate = paymentSettlement.getEndDate();
        int page = paymentSettlement.getPage();
        int size = paymentSettlement.getSize();

        Response<List<ResponsePaymentSettlements>> response = tossClient.paymentSettlements(startDate, endDate, page, size).execute();
        if(response.isSuccessful() && response.body() != null && !response.body().isEmpty())  {
            return response.body();
        }

        throw new IOException(response.message());
    }
}
