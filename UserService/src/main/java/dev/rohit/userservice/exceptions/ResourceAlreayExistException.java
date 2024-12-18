package dev.rohit.userservice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.CONFLICT)
public class ResourceAlreayExistException extends RuntimeException {
    public ResourceAlreayExistException(String message) {
        super(message);
    }
}
