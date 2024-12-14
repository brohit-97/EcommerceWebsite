package dev.rohit.paymentservice.services;

import dev.rohit.paymentservice.dtos.InitiatePaymentRequestDto;
import dev.rohit.paymentservice.dtos.PaymentLinkResponseDto;
import dev.rohit.paymentservice.services.paymentgateway.PaymentGateway;
import dev.rohit.paymentservice.services.paymentgateway.strategies.PaymentGatewaySelectionStrategy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy;


    public PaymentService(PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy) {
        this.paymentGatewaySelectionStrategy = paymentGatewaySelectionStrategy;
    }

    public PaymentLinkResponseDto initiatePayment(Authentication authentication, InitiatePaymentRequestDto initiatePaymentRequestDto) throws Exception {
        // initiate payment
        PaymentGateway paymentGateway = paymentGatewaySelectionStrategy.getbestPaymentGateway();
        // assume order id is 1 and amount is 1100 we will get it from order service api call
        return paymentGateway.generatePaymentLink(1L, 1100L);
    }


}
