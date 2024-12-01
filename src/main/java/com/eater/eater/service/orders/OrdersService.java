package com.eater.eater.service.orders;

import com.eater.eater.model.courier.Courier;
import com.eater.eater.repository.courier.CourierRepository;

public interface OrdersService {
    Courier findCourier(double clientLatitude, double clientLongitude);

}
