package com.eater.eater.dto.auth;

import com.eater.eater.model.courier.CourierRating;
import com.eater.eater.model.orders.Orders;
import lombok.Data;

import java.util.List;

@Data
public class ClientRegistrationRequest {
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String email;
    private String phone;
    private String avatarUrl;
    private String password;
    private List<Orders> orders;
    private List<CourierRating> rating;
}
