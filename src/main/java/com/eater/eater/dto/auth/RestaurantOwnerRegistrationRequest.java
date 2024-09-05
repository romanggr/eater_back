package com.eater.eater.dto.auth;

import com.eater.eater.model.orders.Orders;
import com.eater.eater.model.restaurantOwner.Restaurant;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantOwnerRegistrationRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
    private Restaurant restaurant;
    private List<Orders> orders;
}
