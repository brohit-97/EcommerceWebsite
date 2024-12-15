package dev.rohit.cartservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CartProcessingException extends RuntimeException {
    public CartProcessingException(String message) {
        super(message);
    }
}
