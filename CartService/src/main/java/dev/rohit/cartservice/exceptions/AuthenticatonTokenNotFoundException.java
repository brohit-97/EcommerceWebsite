package dev.rohit.cartservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthenticatonTokenNotFoundException extends RuntimeException {
    public AuthenticatonTokenNotFoundException(String message) {
        super(message);
    }
}