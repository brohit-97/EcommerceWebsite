package dev.rohit.paymentservice.models;

public enum PaymentStatus {

    SUCCESS,
    FAILURE,
    PENDING,
    CANCELLED,
    REFUNDED,
    INITIATED,
    PROCESSING,
    DECLINED,
    TIMEOUT,
    UNKNOWN;


    public static PaymentStatus fromString(String status) {
        try {
            return PaymentStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return PaymentStatus.UNKNOWN;
        }
    }
}
