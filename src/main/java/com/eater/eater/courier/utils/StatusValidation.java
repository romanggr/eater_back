package com.eater.eater.courier.utils;

import com.eater.eater.enums.CourierStatus;
import com.eater.eater.exception.StatusException;


public class StatusValidation {
    public static void validateCourierStatus(CourierStatus courierStatus) {
        if (courierStatus == CourierStatus.UNCONFIRMED) {
            throw new StatusException("Your account is not confirmed yet. Please contact support.");
        }
        if (courierStatus == CourierStatus.ON_DELIVERY) {
            throw new StatusException("You have an order right now. First deliver it and then change the status");
        }
        if (courierStatus == CourierStatus.BANNED) {
            throw new StatusException("Your account was banned please check your email");
        }
    }
}
