package com.wanted.clone.oneport.payments.presentation.port.in;

import sw.sustainable.springlabs.fpay.representation.request.order.CancelOrder;

public interface PaymentCancelUseCase {
    boolean paymentCancel(CancelOrder cancelOrder) throws Exception;
}
