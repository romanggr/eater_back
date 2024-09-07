package com.eater.eater.exception;

public class RestaurantAlreadyCreatedException extends RuntimeException {
    public RestaurantAlreadyCreatedException(String message) {
        super(message);
    }

}
