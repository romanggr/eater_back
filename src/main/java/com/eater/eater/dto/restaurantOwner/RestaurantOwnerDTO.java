package com.eater.eater.dto.restaurantOwner;

import com.eater.eater.model.restaurantOwner.Restaurant;
import lombok.Data;

@Data
public class RestaurantOwnerDTO {
    private Long id;
    private String email;
    private String phone;
    private String name;

    private Restaurant restaurant;
}
