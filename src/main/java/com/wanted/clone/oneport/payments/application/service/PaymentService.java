package com.wanted.clone.oneport.payments.application.service;

import com.wanted.clone.oneport.payments.application.port.out.pg.PaymentAPIs;
import com.wanted.clone.oneport.payments.application.port.out.repository.OrderRepository;
import com.wanted.clone.oneport.payments.application.port.out.repository.PaymentLedgerRepository;
import com.wanted.clone.oneport.payments.application.port.out.repository.TransactionTypeRepository;
import com.wanted.clone.oneport.payments.domain.entity.order.Order;
import com.wanted.clone.oneport.payments.domain.entity.order.OrderStatus;
import com.wanted.clone.oneport.payments.domain.entity.payment.PaymentLedger;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.TossApproveResponseMessage;
import com.wanted.clone.oneport.payments.presentation.port.in.PaymentFullfillUseCase;
import com.wanted.clone.oneport.payments.presentation.web.request.payment.PgCorp;
import com.wanted.clone.oneport.payments.presentation.web.request.payment.ReqPaymentApprove;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements PaymentFullfillUseCase {
    private final Set<PaymentAPIs> paymentAPIsSet;
    private final Set<TransactionTypeRepository> transactionTypeRepositorySet;
    private final OrderRepository orderRepository;
    private final PaymentLedgerRepository paymentLedgerRepository;
    private final OrderService orderService;

    private final Map<String, TransactionTypeRepository> transactionTypeRepositories = new HashMap<>();
    private final Map<String, PaymentAPIs> pgAPIs = new HashMap<>();

    @PostConstruct
    public void init() {
        for (PaymentAPIs paymentAPI : paymentAPIsSet) {
            String pgCorpName = paymentAPI.getClass().getSimpleName().split("Payment")[0].toLowerCase();
            pgAPIs.put(pgCorpName, paymentAPI);
        }

        for (TransactionTypeRepository transactionTypeRepository : transactionTypeRepositorySet) {
            String paymentMethodType = transactionTypeRepository.getClass().getSimpleName().split("TransactionTypeRepository")[0].toLowerCase();
            transactionTypeRepositories.put(paymentMethodType, transactionTypeRepository);
        }
    }

    @Transactional
    @Override
    public String paymentApproved(ReqPaymentApprove requestMessage) throws IOException {
        verifyOrderIsCompleted(requestMessage.getOrderId());
        PaymentAPIs paymentAPIs = selectPgAPI(requestMessage.getSelectedPgCorp());
        TossApproveResponseMessage response = paymentAPIs.requestPaymentApprove(requestMessage);

        if (paymentAPIs.isPaymentApproved(response.getStatus())) {
            Order completedOrder = orderRepository.findById(response.getOrderId());
            completedOrder.orderPaymentFullFill(response.getPaymentKey());
            paymentLedgerRepository.save(response.toEntity());

            return "success";
        }

        return "fail";
    }

    public PaymentLedger getLatestPaymentInfoOnlyOne(String paymentKey) {
        return paymentLedgerRepository.findOneByTransactionIdDesc(paymentKey);
    }

    private void verifyOrderIsCompleted(String orderId) throws IllegalArgumentException {
        OrderStatus status = orderRepository.findById(orderId).getStatus();
        if (!status.equals(OrderStatus.ORDER_COMPLETED))
            throw new IllegalArgumentException("Order is not completed || Order is already paymented");
    }

    public PaymentAPIs selectPgAPI(PgCorp pgCorp) {
        return switch (pgCorp.name().toUpperCase()) {
            case "TOSS" -> pgAPIs.get("TOSS");
            default -> throw new IllegalArgumentException("Invalid pgCorp name: " + pgCorp.name());
        };
    }


}
