package dev.rohit.paymentservice.controllers;

import dev.rohit.paymentservice.dtos.InitiatePaymentRequestDto;
import dev.rohit.paymentservice.dtos.PaymentLinkResponseDto;
import dev.rohit.paymentservice.dtos.PaymentResponseDto;
import dev.rohit.paymentservice.models.Payment;
import dev.rohit.paymentservice.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> getPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<PaymentResponseDto> getByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.getByOrderId(orderId));
    }

    // Get payment by reference id
    @GetMapping("/link/{link}")
    public ResponseEntity<PaymentResponseDto> getByPaymentLink(@PathVariable String link) {
        return ResponseEntity.ok(paymentService.getByPaymentLink(link));
    }


}
