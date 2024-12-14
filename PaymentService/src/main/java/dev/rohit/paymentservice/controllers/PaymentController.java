package dev.rohit.paymentservice.controllers;

import dev.rohit.paymentservice.dtos.InitiatePaymentRequestDto;
import dev.rohit.paymentservice.dtos.PaymentLinkResponseDto;
import dev.rohit.paymentservice.services.PaymentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/payments")
public class PaymentController {


    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate")
    public PaymentLinkResponseDto initiatePaymentLink(Authentication authentication, @RequestBody InitiatePaymentRequestDto initiatePaymentRequestDto) throws Exception {
        // initiate payment
        return paymentService.initiatePayment(authentication,initiatePaymentRequestDto);

    }

}
