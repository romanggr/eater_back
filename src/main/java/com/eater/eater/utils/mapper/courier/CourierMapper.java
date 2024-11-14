package com.eater.eater.utils.mapper.courier;

import com.eater.eater.dto.admin.CouriersForAdminDTO;
import com.eater.eater.dto.auth.CourierRegistrationRequest;
import com.eater.eater.dto.courier.CourierCoordinatesDTO;
import com.eater.eater.dto.courier.CourierDTO;
import com.eater.eater.dto.courier.UpdateCourierRequest;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.courier.CourierCoordinates;
import com.eater.eater.model.courier.CourierRating;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class CourierMapper {

    public static CourierDTO toDTO(Courier courier) {
        if (courier == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
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
            courierDTO.setCoordinatesLastUpdate(courier.getCoordinates().getLastUpdate());
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
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
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

    public static Courier authToEntity(CourierRegistrationRequest input, PasswordEncoder passwordEncoder, String avatarUrl) {
        if (input == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        Courier courier = new Courier();
        courier.setName(input.getName());
        courier.setEmail(input.getEmail());
        courier.setPhone(input.getPhone());
        courier.setPassword(passwordEncoder.encode(input.getPassword()));
        courier.setAvatarUrl(avatarUrl);
        courier.setTransportType(input.getTransportType());


        CourierCoordinates coordinates = new CourierCoordinates();
        coordinates.setCourier(courier);
        coordinates.setLatitude(input.getCoordinatesLatitude());
        coordinates.setLongitude(input.getCoordinatesLongitude());
        coordinates.setLastUpdate(input.getCoordinatesLastUpdate());

        courier.setCoordinates(coordinates);

        return courier;
    }

    public static Courier updateRequestToEntity(UpdateCourierRequest updateCourierRequest, Courier courier) {
        if (updateCourierRequest == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        courier.setName(updateCourierRequest.getName());
        courier.setEmail(updateCourierRequest.getEmail());
        courier.setPhone(updateCourierRequest.getPhone());
        courier.setAvatarUrl(updateCourierRequest.getAvatarUrl());
        courier.setTransportType(updateCourierRequest.getTransportType());

        return courier;
    }
}
