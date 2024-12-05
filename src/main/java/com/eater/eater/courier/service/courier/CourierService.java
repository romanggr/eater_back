package com.eater.eater.courier.service.courier;

import com.eater.eater.courier.dto.CourierCoordinatesDTO;
import com.eater.eater.courier.dto.CourierDTO;
import com.eater.eater.courier.dto.CourierIsActiveDTO;

public interface CourierService {

    CourierDTO updateCourierCoordinates(CourierCoordinatesDTO courierCoordinatesDTO);

    CourierDTO updateIsActive(CourierIsActiveDTO isActiveDTO);


}
