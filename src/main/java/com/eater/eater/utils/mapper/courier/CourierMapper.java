package com.eater.eater.utils.mapper.courier;

import com.eater.eater.dto.courier.CourierCoordinatesDTO;
import com.eater.eater.dto.courier.CourierDTO;
import com.eater.eater.dto.courier.UpdateCourierRequest;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.courier.CourierCoordinates;
import com.eater.eater.model.courier.CourierRating;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CourierMapper {

    public CourierDTO toDTO(Courier courier) {
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
        courierDTO.setIsActive(courier.isActive());

        if (courier.getRating() != null) {
            courierDTO.setRating(courier.getRating().getRating());
        }

        if (courier.getCoordinates() != null) {
            courierDTO.setLatitude(courier.getCoordinates().getLatitude());
            courierDTO.setLongitude(courier.getCoordinates().getLongitude());
            courierDTO.setLastUpdate(courier.getCoordinates().getLastUpdate());
        }

        return courierDTO;
    }

    public Courier toEntity(CourierDTO courierDTO, Courier courier) {
        if (courierDTO == null) {
            return null;
        }

        courier.setId(courierDTO.getId());
        courier.setName(courierDTO.getName());
        courier.setEmail(courierDTO.getEmail());
        courier.setPhone(courierDTO.getPhone());
        courier.setAvatarUrl(courierDTO.getAvatarUrl());
        courier.setTransportType(courierDTO.getTransportType());
        courier.setActive(courierDTO.getIsActive());

        if (courierDTO.getRating() != 0) {
            CourierRating rating = new CourierRating();
            rating.setRating(courierDTO.getRating());
            courier.setRating(rating);
        }

        if (courierDTO.getLatitude() != null ) {
            CourierCoordinates coordinates = new CourierCoordinates();
            coordinates.setLatitude(courierDTO.getLatitude());
            coordinates.setLongitude(courierDTO.getLongitude());
            coordinates.setLastUpdate(courierDTO.getLastUpdate());
        }

        return courier;
    }

    public Courier updateRequestToEntity(UpdateCourierRequest updateCourierRequest, Courier courier) {
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

    public CourierCoordinates coordinatesToEntity(CourierCoordinates courierCoordinates, CourierCoordinatesDTO courierCoordinatesDTO){
        courierCoordinates.setLatitude(courierCoordinatesDTO.getLatitude());
        courierCoordinates.setLongitude(courierCoordinatesDTO.getLongitude());
        courierCoordinates.setLastUpdate(courierCoordinatesDTO.getLastUpdate());

        return courierCoordinates;
    }
}
