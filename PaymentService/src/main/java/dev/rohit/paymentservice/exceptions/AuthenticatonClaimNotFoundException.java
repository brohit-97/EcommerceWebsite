package dev.rohit.paymentservice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class AuthenticatonClaimNotFoundException extends RuntimeException {
    public AuthenticatonClaimNotFoundException(String message) {
        super(message);
    }
}
