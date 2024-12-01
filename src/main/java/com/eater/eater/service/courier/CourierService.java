package com.eater.eater.service.courier;

import com.eater.eater.dto.courier.CourierCoordinatesDTO;
import com.eater.eater.dto.courier.CourierDTO;
import com.eater.eater.dto.courier.CourierIsActiveDTO;
import com.eater.eater.dto.orders.OrderDTOCourier;
import com.eater.eater.dto.orders.OrderHistoryCourierDTO;

import java.util.List;

public interface CourierService {
    CourierDTO getCourier();

    CourierDTO updateCourierCoordinates(CourierCoordinatesDTO courierCoordinatesDTO);

    CourierDTO updateIsActive(CourierIsActiveDTO isActiveDTO);

    OrderDTOCourier getOrder();

    Long setOrderDelivered(Long id);

    List<OrderHistoryCourierDTO> getOrdersHistory();
}
