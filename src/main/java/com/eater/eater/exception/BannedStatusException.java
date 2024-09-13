package com.eater.eater.exception;

public class BannedStatusException extends RuntimeException {
    public BannedStatusException(String message) {
        super(message);
    }

}
