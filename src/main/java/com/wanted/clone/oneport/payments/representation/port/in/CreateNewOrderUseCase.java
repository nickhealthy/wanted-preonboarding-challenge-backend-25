package com.wanted.clone.oneport.payments.representation.port.in;

import com.wanted.clone.oneport.payments.domain.entity.order.Order;
import com.wanted.clone.oneport.payments.representation.web.request.order.PurchaseOrder;

public interface CreateNewOrderUseCase {
    Order createOrder(PurchaseOrder newOrder) throws Exception;
}
