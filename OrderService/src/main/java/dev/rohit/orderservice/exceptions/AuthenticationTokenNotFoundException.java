package dev.rohit.orderservice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class AuthenticationTokenNotFoundException extends RuntimeException {
    public AuthenticationTokenNotFoundException(String message) {
        super(message);
    }
}
