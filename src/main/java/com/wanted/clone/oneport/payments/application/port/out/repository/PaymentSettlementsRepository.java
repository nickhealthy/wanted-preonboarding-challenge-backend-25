package com.wanted.clone.oneport.payments.application.port.out.repository;

import sw.sustainable.springlabs.fpay.domain.settlements.PaymentSettlements;

import java.util.List;

public interface PaymentSettlementsRepository {
    PaymentSettlements findById(String paymentKey);
    void bulkInsert(List<PaymentSettlements> paymentSettlements);
}
