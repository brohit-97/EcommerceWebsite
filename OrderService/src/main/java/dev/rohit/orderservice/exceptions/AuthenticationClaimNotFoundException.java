package dev.rohit.orderservice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class AuthenticationClaimNotFoundException extends RuntimeException {
    public AuthenticationClaimNotFoundException(String message) {
        super(message);
    }
}
