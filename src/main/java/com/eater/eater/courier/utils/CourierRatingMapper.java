package com.eater.eater.courier.utils;

import com.eater.eater.courier.dto.CourierRatingDTO;
import com.eater.eater.client.model.Client;
import com.eater.eater.courier.model.Courier;
import com.eater.eater.courier.model.CourierRating;

public class CourierRatingMapper {
    public static CourierRatingDTO toDTO(CourierRating courierRating){
        if(courierRating == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        CourierRatingDTO courierRatingDTO = new CourierRatingDTO();
        courierRatingDTO.setRating(courierRating.getRating());
        courierRatingDTO.setCourierId(courierRating.getCourier().getId());
        courierRatingDTO.setClientId(courierRating.getClient().getId());

        return courierRatingDTO;
    }

    public static CourierRating toEntity(CourierRatingDTO courierRatingDTO, Courier courier, Client client) {
        if (courierRatingDTO == null) {
            throw new IllegalArgumentException("Parameter cannot be null or empty.");
        }

        CourierRating courierRating = new CourierRating();
        courierRating.setRating(courierRatingDTO.getRating());
        courierRating.setCourier(courier);
        courierRating.setClient(client);

        return courierRating;
    }
}
