package com.eater.eater.service.client;

import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.courier.CourierCoordinatesDTO;
import com.eater.eater.dto.courier.CourierRatingDTO;


public interface ClientService {

    ClientDTO getClient();

    CourierCoordinatesDTO getCourierCoordinates(Long courierId);

    CourierRatingDTO setCourierRating(CourierRatingDTO courierRatingDTO);

}
