package com.eater.eater.exception;

public class NoAvailableCouriersException extends RuntimeException {
    public NoAvailableCouriersException(String message) {
        super(message);
    }
}
