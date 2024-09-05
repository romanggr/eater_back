package com.eater.eater.exception;

public class PhoneAlreadyInUseException extends RuntimeException {
    public PhoneAlreadyInUseException(String message) {
        super(message);
    }
}