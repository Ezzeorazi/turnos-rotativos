package com.example.turnos_rotativos.exceptions;

import org.springframework.http.HttpStatus;

public class BussinessException extends RuntimeException {
    private HttpStatus status;

    public BussinessException() {
        super();
    }

    public BussinessException(String message) {
        super(message);
    }

    public BussinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BussinessException(Throwable cause) {
        super(cause);
    }

    public BussinessException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}