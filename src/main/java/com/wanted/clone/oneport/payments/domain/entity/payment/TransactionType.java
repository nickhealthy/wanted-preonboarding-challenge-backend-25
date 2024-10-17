package com.wanted.clone.oneport.payments.domain.entity.payment;

import com.wanted.clone.oneport.payments.domain.entity.payment.card.CardPayment;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.ResponsePaymentApproved;
import com.wanted.clone.oneport.payments.infrastructure.pg.toss.response.payment.ResponsePaymentCommon;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class TransactionType {
    public static TransactionType convertToTransactionType(ResponsePaymentCommon commonMessage) {
        return switch (commonMessage.getMethod()) {
            case "카드" -> CardPayment.from((ResponsePaymentApproved) commonMessage);
            default ->
                throw new RuntimeException("Unsupported TransactionType method ::: " + commonMessage.getMethod());
        };
    }
}
