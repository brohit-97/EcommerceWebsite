package dev.rohit.productservice.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException e) {
//        return ResponseEntity.status(404).body(new ExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND));
//    }


}
