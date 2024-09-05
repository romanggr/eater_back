package com.eater.eater.dto.auth;

import com.eater.eater.enums.TransportType;
import com.eater.eater.model.orders.Orders;
import com.eater.eater.model.courier.CourierCoordinates;
import com.eater.eater.model.courier.CourierRating;
import lombok.Data;

@Data
public class CourierRegistrationRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String avatarUrl;
    private TransportType transportType;
    private Orders orders;
    private CourierCoordinates courierCoordinates;
    private CourierRating courierRating;
}
