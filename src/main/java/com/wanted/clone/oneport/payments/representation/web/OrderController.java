package com.wanted.clone.oneport.payments.representation.web;

import com.wanted.clone.oneport.payments.representation.port.in.CreateNewOrderUseCase;
import com.wanted.clone.oneport.payments.representation.web.request.order.PurchaseOrder;
import com.wanted.clone.oneport.payments.representation.web.response.RespNewPurchaseOrderMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final CreateNewOrderUseCase createNewOrderUseCase;

    @PostMapping("/new")
    public RespNewPurchaseOrderMessage newOrder(@RequestBody @Valid PurchaseOrder newOrder) throws Exception {
        return RespNewPurchaseOrderMessage.from(createNewOrderUseCase.createOrder(newOrder));
    }

    @GetMapping
    public String test() throws Exception {
        return "test";
    }

    @GetMapping("info")
    public  Map<String, String> requestParams(@RequestParam(value = "username") String username){
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        return params;
    }

}
