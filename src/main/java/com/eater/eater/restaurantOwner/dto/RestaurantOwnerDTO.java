package com.eater.eater.restaurantOwner.dto;

import com.eater.eater.restaurantOwner.model.Restaurant;
import lombok.Data;

@Data
public class RestaurantOwnerDTO {
    private Long id;
    private String email;
    private String phone;
    private String name;

    private Restaurant restaurant;
}
