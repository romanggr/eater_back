package com.eater.eater.restaurantOwner.dto;

import lombok.Data;

@Data
public class UpdateRestaurantOwnerRequest {
    private String email;
    private String phone;
    private String name;
    private String password;
}
