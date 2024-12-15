package dev.rohit.paymentservice.dtos;

import dev.rohit.paymentservice.models.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {
    private Long orderId;
    private Double amount;
    private String currency;
    private String paymentLink;
    private String paymentId;
    private String paymentStatus;

    public static PaymentResponseDto buildFromPayment(Payment payment) {
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setOrderId(payment.getOrderId());
        paymentResponseDto.setAmount(payment.getAmount());
        paymentResponseDto.setCurrency(payment.getCurrency());
        paymentResponseDto.setPaymentLink(payment.getPaymentLink());
        paymentResponseDto.setPaymentId(payment.getPaymentId());
        paymentResponseDto.setPaymentStatus(payment.getPaymentStatus().name());
        return paymentResponseDto;
    }

}
