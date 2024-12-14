package dev.rohit.paymentservice.services;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String initiatePayment(String orderId) {
        // initiate payment
        return "Payment initiated for order: " + orderId;
    }


}
