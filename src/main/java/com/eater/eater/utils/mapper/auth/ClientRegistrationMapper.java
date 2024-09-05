package com.eater.eater.utils.mapper.auth;

import com.eater.eater.dto.auth.ClientRegistrationRequest;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.CourierRating;
import com.eater.eater.model.orders.Orders;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class ClientRegistrationMapper {
    public static Client toEntity(ClientRegistrationRequest input, PasswordEncoder passwordEncoder) {
        Client client = new Client();
        client.setName(input.getName());
        client.setAddress(input.getAddress());
        client.setLatitude(input.getLatitude());
        client.setLongitude(input.getLongitude());
        client.setEmail(input.getEmail());
        client.setPhone(input.getPhone());
        client.setAvatarUrl(input.getAvatarUrl());
        client.setPassword(passwordEncoder.encode(input.getPassword()));
        client.setOrders((List<Orders>) input.getOrders());
        client.setRating((List<CourierRating>) input.getRating());

        return client;
    }
}
