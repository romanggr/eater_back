package com.eater.eater.client.service.client;

import com.eater.eater.courier.dto.CourierCoordinatesDTO;
import com.eater.eater.courier.dto.CourierRatingDTO;


public interface ClientService {

    CourierCoordinatesDTO getCourierCoordinates(Long courierId);

    CourierRatingDTO setCourierRating(CourierRatingDTO courierRatingDTO);

}
