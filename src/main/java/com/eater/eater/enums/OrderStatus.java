package com.eater.eater.enums;

public enum OrderStatus {
    CREATED,
    APPROVED_BY_RESTAURANT, // courier get order
    APPROVED_BY_COURIER, // courier delivered order
    FINISHED // client approved order delivering
}
