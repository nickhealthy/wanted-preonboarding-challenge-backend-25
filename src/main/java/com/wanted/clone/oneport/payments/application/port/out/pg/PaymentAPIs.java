package com.wanted.clone.oneport.payments.application.port.out.pg;

import com.wanted.clone.oneport.payments.infrastructure.pg.CommonApproveMessage;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.ResponsePaymentApproved;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.ResponsePaymentCancel;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.ResponsePaymentSettlements;
import com.wanted.clone.oneport.payments.presentation.web.request.payment.ReqPaymentApprove;

import java.io.IOException;
import java.util.List;

public interface PaymentAPIs {
    ResponsePaymentApproved requestPaymentApprove(ReqPaymentApprove requestMessage) throws IOException;

    boolean isPaymentApproved(String status);

    ResponsePaymentCancel requestPaymentCancel(String orderId) throws IOException;

    List<ResponsePaymentSettlements> requestPaymentSettlement() throws IOException;
}
