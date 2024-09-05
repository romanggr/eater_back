package com.eater.eater.utils.mapper.auth;

import com.eater.eater.dto.auth.CourierRegistrationRequest;
import com.eater.eater.model.courier.CourierCoordinates;
import com.eater.eater.model.orders.Orders;
import com.eater.eater.model.courier.Courier;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class CourierRegistrationMapper {
    public static Courier toEntity(CourierRegistrationRequest input, PasswordEncoder passwordEncoder) {
        if (input == null) {
            return null;
        }

        Courier courier = new Courier();
        courier.setName(input.getName());
        courier.setEmail(input.getEmail());
        courier.setPhone(input.getPhone());
        courier.setPassword(passwordEncoder.encode(input.getPassword()));
        courier.setAvatarUrl(input.getAvatarUrl());
        courier.setRating(input.getCourierRating());
        courier.setOrders((List<Orders>) input.getOrders());
        courier.setTransportType(input.getTransportType());

        // Handle CourierCoordinates
        CourierCoordinates coordinates = input.getCourierCoordinates();
        if (coordinates != null) {
            coordinates.setCourier(courier);
            courier.setCoordinates(coordinates);
        }

        return courier;
    }
}
