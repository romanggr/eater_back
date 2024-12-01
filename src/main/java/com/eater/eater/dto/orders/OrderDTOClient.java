package com.eater.eater.dto.orders;

import com.eater.eater.dto.courier.CourierCoordinatesDTO;
import com.eater.eater.enums.OrderStatus;
import com.eater.eater.enums.TransportType;
import com.eater.eater.model.courier.CourierCoordinates;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTOClient {
    private Long id; // unique code for getting order
    private Double totalPrice;
    private LocalDateTime createdAt;
    private OrderStatus status;

    private List<MenuResponse> orderMenus;
    private String restaurantName;
    private String restaurantPhone;
    private String restaurantEmail;

    private String courierName;
    private String courierEmail;
    private String courierPhone;
    private String courierAvatarUrl;
    private TransportType courierTransportType;
    private CourierCoordinatesDTO courierCoordinatesDTO;
}
