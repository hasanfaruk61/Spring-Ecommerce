package com.laba.Spring.Ecommerce.odev7.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleException(BusinessException businessException) {
        return new ResponseEntity<>(businessException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
