package com.eater.eater.service.courier;

import com.eater.eater.dto.courier.CourierCoordinatesDTO;
import com.eater.eater.dto.courier.CourierDTO;
import com.eater.eater.dto.courier.CourierIsActiveDTO;

public interface CourierService {
    CourierDTO getCourier();

    CourierDTO updateCourierCoordinates(CourierCoordinatesDTO courierCoordinatesDTO);

    CourierDTO updateIsActive(CourierIsActiveDTO isActiveDTO);
}
