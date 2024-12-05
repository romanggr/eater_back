package com.eater.eater.restaurantOwner.dto;

import lombok.Data;

@Data
public class RestaurantOwnerRegistrationRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
    private int emailCode;
}
