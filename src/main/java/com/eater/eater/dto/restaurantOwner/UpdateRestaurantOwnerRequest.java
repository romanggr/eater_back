package com.eater.eater.dto.restaurantOwner;

import lombok.Data;

@Data
public class UpdateRestaurantOwnerRequest {
    private String email;
    private String phone;
    private String name;
    private String oldPassword;
}
