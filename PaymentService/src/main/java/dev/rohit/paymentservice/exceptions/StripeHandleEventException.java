package dev.rohit.paymentservice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
public class StripeHandleEventException extends RuntimeException {
    public StripeHandleEventException(String message) {
        super(message);
    }
}
