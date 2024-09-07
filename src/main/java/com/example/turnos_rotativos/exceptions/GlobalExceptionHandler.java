package com.example.turnos_rotativos.exceptions;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle BussinessException
    // Return BAD_REQUEST status code
    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<String> handleBussinessException(BussinessException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }




}