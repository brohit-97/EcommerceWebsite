package dev.rohit.paymentservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/payments")
public class PaymentController {

    @PostMapping("/{orderId}")
    public String initiatePayment(@PathVariable String orderId) {
        // initiate payment
        return "Hello" + orderId;
    }

}
