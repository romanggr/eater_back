package com.eater.eater.enums;

public enum CourierStatus {
    AVAILABLE,         // Courier is working and can get delivery
    ON_DELIVERY,       // Courier have delivery now and cannot get one more
    UNCONFIRMED,       // Courier need to be confirmed by admin
    OFF_DUTY,           // Courier isn't working now
    BANNED              // Courier was banned
}