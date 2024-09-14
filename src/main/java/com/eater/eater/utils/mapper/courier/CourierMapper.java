package com.eater.eater.utils.mapper.courier;

import com.eater.eater.dto.admin.CouriersForAdminDTO;
import com.eater.eater.dto.auth.CourierRegistrationRequest;
import com.eater.eater.dto.courier.CourierCoordinatesDTO;
import com.eater.eater.dto.courier.CourierDTO;
import com.eater.eater.dto.courier.UpdateCourierRequest;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.courier.CourierCoordinates;
import com.eater.eater.model.courier.CourierRating;
import com.eater.eater.model.orders.Orders;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourierMapper {

    public static CourierDTO toDTO(Courier courier) {
        if (courier == null) {
            return null;
        }

        CourierDTO courierDTO = new CourierDTO();
        courierDTO.setId(courier.getId());
        courierDTO.setName(courier.getName());
        courierDTO.setEmail(courier.getEmail());
        courierDTO.setPhone(courier.getPhone());
        courierDTO.setAvatarUrl(courier.getAvatarUrl());
        courierDTO.setTransportType(courier.getTransportType());
        courierDTO.setRole(courier.getRole());
        courierDTO.setCourierStatus(courier.getCourierStatus());

        if (courier.getRating() != null) {
            courierDTO.setRating(courier.getRating().stream()
                    .mapToInt(CourierRating::getRating)
                    .average()
                    .orElse(0.0));
        }

        if (courier.getCoordinates() != null) {
            courierDTO.setLatitude(courier.getCoordinates().getLatitude());
            courierDTO.setLongitude(courier.getCoordinates().getLongitude());
            courierDTO.setLastUpdate(courier.getCoordinates().getLastUpdate());
        }

        return courierDTO;
    }


    public static CourierCoordinates coordinatesToEntity(CourierCoordinates courierCoordinates, CourierCoordinatesDTO courierCoordinatesDTO) {
        courierCoordinates.setLatitude(courierCoordinatesDTO.getLatitude());
        courierCoordinates.setLongitude(courierCoordinatesDTO.getLongitude());
        courierCoordinates.setLastUpdate(courierCoordinatesDTO.getLastUpdate());

        return courierCoordinates;
    }

    public static CourierCoordinatesDTO coordinatesToDTO(CourierCoordinates courierCoordinates) {
        CourierCoordinatesDTO courierCoordinatesDTO = new CourierCoordinatesDTO();

        courierCoordinatesDTO.setLatitude(courierCoordinates.getLatitude());
        courierCoordinatesDTO.setLongitude(courierCoordinates.getLongitude());
        courierCoordinatesDTO.setLastUpdate(courierCoordinates.getLastUpdate());

        return courierCoordinatesDTO;
    }

    public static List<CouriersForAdminDTO> allCourierToDTO(List<Courier> couriers) {
        if (couriers == null || couriers.isEmpty()) {
            return new ArrayList<>();
        }

        return couriers.stream().map(courier -> {
            CouriersForAdminDTO dto = new CouriersForAdminDTO();

            dto.setId(courier.getId());
            dto.setPhone(courier.getPhone());
            dto.setAvatarUrl(courier.getAvatarUrl());
            dto.setTransportType(courier.getTransportType());
            dto.setCourierStatus(courier.getCourierStatus());

            return dto;

        }).collect(Collectors.toList());
    }

    public static Courier authToEntity(CourierRegistrationRequest input, PasswordEncoder passwordEncoder) {
        if (input == null) {
            return null;
        }

        Courier courier = new Courier();
        courier.setName(input.getName());
        courier.setEmail(input.getEmail());
        courier.setPhone(input.getPhone());
        courier.setPassword(passwordEncoder.encode(input.getPassword()));
        courier.setAvatarUrl(input.getAvatarUrl());
        courier.setRating((List<CourierRating>) input.getCourierRating());
        courier.setOrders((List<Orders>) input.getOrders());
        courier.setTransportType(input.getTransportType());

        CourierCoordinates coordinates = input.getCourierCoordinates();
        if (coordinates != null) {
            coordinates.setCourier(courier);
            courier.setCoordinates(coordinates);
        }

        return courier;
    }

    public static Courier updateRequestToEntity(UpdateCourierRequest updateCourierRequest, Courier courier) {
        if (updateCourierRequest == null) {
            return null;
        }

        courier.setName(updateCourierRequest.getName());
        courier.setEmail(updateCourierRequest.getEmail());
        courier.setPhone(updateCourierRequest.getPhone());
        courier.setAvatarUrl(updateCourierRequest.getAvatarUrl());
        courier.setTransportType(updateCourierRequest.getTransportType());

        return courier;
    }
}
