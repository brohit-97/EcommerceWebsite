package dev.rohit.paymentservice.services.paymentgateway;

import dev.rohit.paymentservice.dtos.PaymentLinkResponseDto;

public interface PaymentGateway {

    PaymentLinkResponseDto generatePaymentLink(Long orderId, Long amount) throws Exception;

}
