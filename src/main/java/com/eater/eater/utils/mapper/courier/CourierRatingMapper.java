package com.eater.eater.utils.mapper.courier;

import com.eater.eater.dto.courier.CourierRatingDTO;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.courier.CourierRating;
import com.eater.eater.service.client.ClientService;
import org.springframework.stereotype.Component;

@Component
public class CourierRatingMapper {
    public CourierRatingDTO toDTO(CourierRating courierRating){
        if(courierRating == null) return null;

        CourierRatingDTO courierRatingDTO = new CourierRatingDTO();
        courierRatingDTO.setId(courierRating.getId());
        courierRatingDTO.setRating(courierRating.getRating());
        courierRatingDTO.setCourierId(courierRating.getCourier().getId());
        courierRatingDTO.setClientId(courierRating.getClient().getId());

        return courierRatingDTO;
    }

    public CourierRating toEntity(CourierRatingDTO courierRatingDTO, Courier courier, Client client) {
        if (courierRatingDTO == null) return null;

        CourierRating courierRating = new CourierRating();
        courierRating.setId(courierRatingDTO.getId());
        courierRating.setRating(courierRatingDTO.getRating());
        courierRating.setCourier(courier);
        courierRating.setClient(client);

        return courierRating;
    }
}
