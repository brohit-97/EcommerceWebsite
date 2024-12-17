package dev.rohit.orderservice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceCallFailed extends RuntimeException {
    public ServiceCallFailed(String message) {
        super(message);
    }
}
