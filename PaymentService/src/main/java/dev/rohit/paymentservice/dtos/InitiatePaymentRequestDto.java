package dev.rohit.paymentservice.dtos;


public class InitiatePaymentRequestDto {
    private Long orderId;

    public InitiatePaymentRequestDto() {
    }

    public InitiatePaymentRequestDto(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
