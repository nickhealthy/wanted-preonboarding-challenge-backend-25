package com.wanted.clone.oneport.payments.presentation.web;

import com.wanted.clone.oneport.payments.application.service.dto.PaymentRequest;
import com.wanted.clone.oneport.payments.presentation.port.in.*;
import com.wanted.clone.oneport.payments.presentation.web.request.payment.ReqPaymentApprove;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentCommonUseCase paymentCommonUseCase;

    @GetMapping("checkout")
    public String paymentCheckout(@RequestParam(value = "orderId") String orderId,
                                  @RequestParam(value = "ordererName") String ordererName,
                                  @RequestParam(value = "ordererPhoneNumber") String ordererPhoneNumber,
                                  @RequestParam(value = "userId") String userId,
                                  @RequestParam(value = "amount") String amount,
                                  @RequestParam(value = "pgCorpName") String pgCorpName,
                                  Model model) throws Exception {
        model.addAttribute("orderId", orderId);
        model.addAttribute("ordererName", ordererName);
        model.addAttribute("ordererPhoneNumber", ordererPhoneNumber);
        model.addAttribute("userId", userId);
        model.addAttribute("amount", amount);
        return paymentCommonUseCase.renderPgUi(PaymentRequest.of(pgCorpName), "checkout");
    }

    @GetMapping("success")
    public String paymentFullfill(@RequestParam(value = "paymentType") String paymentType,
                                  @RequestParam(value = "orderId") String orderId,
                                  @RequestParam(value = "paymentKey") String paymentKey,
                                  @RequestParam(value = "amount") String amount,
                                  @RequestParam(value = "pgCorpName") String pgCorpName
    ) throws Exception {
        return paymentCommonUseCase.renderPgUi(PaymentRequest.of(pgCorpName), "success");
    }

    @GetMapping("fail")
    public String paymentFail(@RequestParam(value = "message") String message,
                              @RequestParam(value = "message") String pgCorpName) throws Exception {
        return paymentCommonUseCase.renderPgUi(PaymentRequest.of(pgCorpName), "fail");
    }

    @PostMapping("confirm")
    public String paymentApprove(@RequestBody ReqPaymentApprove message) {
        log.info("message -> {}", message);
        return "toss/fail";
    }

}
