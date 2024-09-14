package com.eater.eater.utils.mapper.courier;

import com.eater.eater.dto.courier.CourierRatingDTO;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.courier.CourierRating;

public class CourierRatingMapper {
    public static CourierRatingDTO toDTO(CourierRating courierRating){
        if(courierRating == null) return null;

        CourierRatingDTO courierRatingDTO = new CourierRatingDTO();
        courierRatingDTO.setRating(courierRating.getRating());
        courierRatingDTO.setCourierId(courierRating.getCourier().getId());
        courierRatingDTO.setClientId(courierRating.getClient().getId());

        return courierRatingDTO;
    }

    public static CourierRating toEntity(CourierRatingDTO courierRatingDTO, Courier courier, Client client) {
        if (courierRatingDTO == null) return null;

        CourierRating courierRating = new CourierRating();
        courierRating.setRating(courierRatingDTO.getRating());
        courierRating.setCourier(courier);
        courierRating.setClient(client);

        return courierRating;
    }
}
